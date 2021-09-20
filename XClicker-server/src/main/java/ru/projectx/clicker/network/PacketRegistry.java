package ru.projectx.clicker.network;

import ru.projectx.clicker.network.packets.*;
import ru.projectx.clicker.utils.LogUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PacketRegistry {
    private static final HashMap<Integer, Class<? extends IPacket>> packets = new HashMap<>();

    public static void init() {
        LogUtils.info("Register packets...");
        packets.put(1, AuthPacket.class);
        packets.put(2, ClickEnemyPacket.class);
        packets.put(3, QuitPacket.class);
        packets.put(4, SyncEnemyPacket.class);
        packets.put(5, SyncPlayerStatsPacket.class);
        LogUtils.info("Packets successfully registered");
    }

    public static IPacket getPacket(int id) {
        try {
            return packets.get(id).getConstructor().newInstance();
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getId(IPacket packet) {
        return packets.entrySet().stream().filter(entry -> packet.getClass().equals(entry.getValue())).findFirst().map(Map.Entry::getKey).orElse(0);
    }
 }
