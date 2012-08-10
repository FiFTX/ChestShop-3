package com.Acrobot.ChestShop.Listeners.Shop.PostTransaction;

import com.Acrobot.ChestShop.Config.Config;
import com.Acrobot.ChestShop.Config.Property;
import com.Acrobot.ChestShop.Events.TransactionEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author Acrobot
 */
public class EmptyShopDeleter implements Listener {
    @EventHandler
    public static void onTransaction(TransactionEvent event) {
        if (event.getTransactionType() != TransactionEvent.TransactionType.BUY) {
            return;
        }

        if (shopShouldBeRemoved(event.getOwnerInventory())) {
            event.getSign().getBlock().setType(Material.AIR);
            event.getOwnerInventory().addItem(new ItemStack(Material.SIGN, 1));
        }
    }

    private static boolean shopShouldBeRemoved(Inventory inventory) {
        return Config.getBoolean(Property.REMOVE_EMPTY_SHOPS) && isEmpty(inventory);
    }

    public static boolean isEmpty(Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                return false;
            }
        }

        return true;
    }
}
