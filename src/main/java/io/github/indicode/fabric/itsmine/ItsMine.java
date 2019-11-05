package io.github.indicode.fabric.itsmine;

import io.github.indicode.fabric.permissions.PermChangeBehavior;
import io.github.indicode.fabric.permissions.Thimble;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/**
 * @author Indigo Amann
 */
public class ItsMine implements ModInitializer {
    @Override
    public void onInitialize() {
        Config.sync(false);
        Thimble.permissionWriters.add((map, server) -> {
            map.registerPermission("itsmine.specify_groups", PermChangeBehavior.UPDATE_COMMAND_TREE);
            map.registerPermission("itsmine.admin.infinite_claim", PermChangeBehavior.UPDATE_COMMAND_TREE);
            map.registerPermission("itsmine.admin.check_others", PermChangeBehavior.UPDATE_COMMAND_TREE);
            map.registerPermission("itsmine.admin.modify_balance", PermChangeBehavior.UPDATE_COMMAND_TREE);
            map.registerPermission("itsmine.admin.modify", PermChangeBehavior.UPDATE_COMMAND_TREE);
            map.registerPermission("itsmine.admin.modify_permissions", PermChangeBehavior.UPDATE_COMMAND_TREE);
            map.registerPermission("itsmine.admin.ignore_claims", PermChangeBehavior.UPDATE_COMMAND_TREE);
        });
    }
    public static String blocksToAreaString(int blocks) {
        int base = (int) Math.floor(Math.cbrt(blocks));
        int additionalBlocks = blocks - (int) Math.pow(base, 3);
        int extraRows = (int) Math.floor(Math.cbrt(Math.floor((float)additionalBlocks / base)));
        int leftoverBlocks = additionalBlocks % base;
        return (base + extraRows) + "x" + base + "x" + base + "(+" + leftoverBlocks + ")";
    }
}
