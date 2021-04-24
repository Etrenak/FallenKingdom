package fr.devsylone.fallenkingdom.team;

import fr.devsylone.fallenkingdom.UUIDService;
import fr.devsylone.fkpi.team.Base;
import fr.devsylone.fkpi.team.FkTeam;
import fr.devsylone.fkpi.team.TeamChangeResult;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class FkTeamImpl implements FkTeam {

    private final TextColor color;
    private final String name;
    private final Component displayName;
    private final Map<UUID, String> players;
    private @Nullable Base base;
    private TeamListener listener;

    private final UUIDService uuidService;

    FkTeamImpl(
            @NotNull TextColor color,
            @NotNull String name,
            @NotNull Component displayName,
            @NotNull TeamManagerImpl teamManager
    ) {
        this(color, name, displayName, teamManager.uuidService());
    }

    FkTeamImpl(
            @NotNull TextColor color,
            @NotNull String name,
            @NotNull Component displayName,
            @NotNull UUIDService uuidService
    ) {
        this.color = color;
        this.name = name;
        this.displayName = displayName;
        this.players = new ConcurrentHashMap<>();
        this.listener = TeamListener.ALWAYS_TRUE;
        this.uuidService = uuidService;
    }

    @Override
    public @NotNull TeamChangeResult addPlayer(@NotNull String playerName) {
        final UUID playerUniqueId = this.uuidService.playerUniqueId(playerName);
        if (playerUniqueId == null) {
            return TeamChangeResult.missingPlayerId();
        }
        TeamChangeResult result = this.listener.onPlayerAdd(this, playerUniqueId);
        if (result.isSuccess() && this.players.put(playerUniqueId, playerName) != null) {
            return TeamChangeResult.alreadyIn();
        }
        return result;
    }

    @Override
    public @NotNull TeamChangeResult addPlayer(@NotNull UUID playerUniqueId) {
        final String playerName = this.uuidService.playerName(playerUniqueId);
        if (playerName == null) {
            return TeamChangeResult.missingPlayerName();
        }
        TeamChangeResult result = this.listener.onPlayerAdd(this, playerUniqueId);
        if (result.isSuccess() && this.players.put(playerUniqueId, playerName) != null) {
            return TeamChangeResult.alreadyIn();
        }
        return result;
    }

    @Override
    public boolean hasPlayer(@NotNull String playerName) {
        return this.players.containsValue(playerName);
    }

    @Override
    public boolean hasPlayer(@NotNull UUID playerUniqueId) {
        return this.players.containsKey(playerUniqueId);
    }

    @Override
    public boolean removePlayer(@NotNull String playerName) {
        final UUID playerUniqueId = this.uuidService.playerUniqueId(playerName);
        if (playerUniqueId != null) {
            return this.removePlayer(playerUniqueId);
        }
        return false;
    }

    @Override
    public boolean removePlayer(@NotNull UUID playerUniqueId) {
        if (this.listener.onPlayerRemove(this, playerUniqueId)) {
            return this.players.remove(playerUniqueId) != null;
        }
        return false;
    }

    @Override
    public @NotNull Set<UUID> playersUniqueIds() {
        return Collections.unmodifiableSet(this.players.keySet());
    }

    @Override
    public @NotNull Set<String> playersNames() {
        return Collections.unmodifiableSet(new HashSet<>(this.players.values()));
    }

    @Override
    public int size() {
        return this.players.size();
    }

    @Override
    public boolean isEmpty() {
        return this.players.isEmpty();
    }

    @Override
    public @NotNull Optional<@NotNull Base> base() {
        return Optional.ofNullable(this.base);
    }

    @Override
    public void base(@Nullable Base base) {
        this.base = base;
    }

    @Override
    public @NotNull TextColor color() {
        return this.color;
    }

    @Override
    public @NotNull String name() {
        return this.name;
    }

    @NotNull
    @Override
    public Iterator<UUID> iterator() {
        return this.players.keySet().iterator();
    }

    @Override
    public @NotNull Component asComponent() {
        return this.displayName;
    }

    @Override
    public @NotNull FkTeam.Builder toBuilder() {
        return new TeamBuilderImpl(this.color, this.name, this.displayName, this.players, this.uuidService);
    }

    void setListener(@NotNull TeamListener listener) {
        this.listener = requireNonNull(listener, "listener");
    }

    @Override
    public String toString() {
        return "FkTeam{" +
                "color=" + this.color +
                ", players=" + this.players.values() +
                ", base=" + (this.base != null) +
                '}';
    }
}