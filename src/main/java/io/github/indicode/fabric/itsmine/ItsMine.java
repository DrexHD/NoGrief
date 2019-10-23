package io.github.indicode.fabric.itsmine;

import io.github.indicode.fabric.permissions.Thimble;
import io.github.indicode.fabric.permissions.command.CommandPermission;
import io.github.indicode.fabric.permissions.command.NoSavePermission;
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
            try {
                map.getPermission("itsmine", CommandPermission.class);
                map.getPermission("itsmine.specify_groups", CommandPermission.class);
                map.getPermission("itsmine.admin", CommandPermission.class);
                map.getPermission("itsmine.admin.infinite_claim", CommandPermission.class);
                map.getPermission("itsmine.admin.check_others", CommandPermission.class);
                map.getPermission("itsmine.admin.modify_balance", CommandPermission.class);
                map.getPermission("itsmine.admin.modify", CommandPermission.class);
                map.getPermission("itsmine.admin.ignore_claims", CommandPermission.class);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                System.err.println("Claim permissions could not be loaded:");
                e.printStackTrace();
            }
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
