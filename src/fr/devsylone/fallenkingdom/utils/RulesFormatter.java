package fr.devsylone.fallenkingdom.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.devsylone.fallenkingdom.Fk;
import fr.devsylone.fkpi.rules.AllowedBlocks;
import fr.devsylone.fkpi.rules.ChargedCreepers;
import fr.devsylone.fkpi.rules.DenyPotions;
import fr.devsylone.fkpi.rules.PlaceBlockInCave;
import fr.devsylone.fkpi.rules.Rule;
import fr.devsylone.fkpi.util.BlockDescription;

public class RulesFormatter
{
	private static String format(Rule rule)
	{
		String format = "§6" + rule.getName() + " » ";
		String value = "";

		if(rule instanceof ChargedCreepers)
		{
			ChargedCreepers cgRule = (ChargedCreepers) rule;
			value = "§e" + cgRule.getSpawn() + "% spawn - " + cgRule.getDrop() + "% drop - " + cgRule.getTntAmount() + " tnt(s)";
		}

		else if(rule instanceof PlaceBlockInCave)
		{
			PlaceBlockInCave pbicRule = (PlaceBlockInCave) rule;

			value = "§e" + pbicRule.getValue() + ((boolean) pbicRule.getValue() ? " - " + pbicRule.getMinimumBlocks() + " blocs" : "");
		}

		else if(rule instanceof AllowedBlocks)
		{
			for(BlockDescription b : ((AllowedBlocks) rule).reducedList())
				value += ("\n" + "§b> §2" + b.toString());
		}

		else if(rule instanceof DenyPotions)
		{
			if((boolean) rule.getValue())
				value = "§4toutes";
			else
				if (((DenyPotions) rule).getAdvancedList().size() < 1)
					value = "§efalse";
				for(String disallowed : ((DenyPotions) rule).getAdvancedList())
					value += ("\n" + "§4> §c" + disallowed);
		}

		else if(rule.getValue() instanceof Boolean)
			value = (Boolean) rule.getValue() ? "§2✔" : "§4✘";

		else if(rule.getValue() instanceof Integer)
			value = "§e" + rule.getValue();

		return format + value;
	}

	public static List<String> formatRules(String... withouts)
	{
		List<String> withoutsList = Arrays.asList(withouts);
		List<String> response = new ArrayList<String>();

		for(Rule rule : Fk.getInstance().getFkPI().getRulesManager().getRulesList())
			if(!withoutsList.contains(rule.getName().toLowerCase()))
				response.add(format(rule));

		return response;
	}

	public static List<String> formatRules()
	{
		return formatRules(new String[0]);
	}
}
