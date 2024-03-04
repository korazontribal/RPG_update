package items.potions;

import player.Player;
import util.Usable;

public record HealPotion(PotionSize size) implements Usable {

	@Override
	public void use(Player player) {

		System.out.println("You have used a " + size + " heal potion.");
		player.heal(size.getAmount());
	}
}