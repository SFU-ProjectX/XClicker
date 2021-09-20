package ru.projectx.clicker.network;

import io.netty.channel.Channel;
import ru.projectx.clicker.data.Player;
import ru.projectx.clicker.network.packets.IPacket;

import java.util.Objects;
import java.util.Optional;

public class ServerUser {
    private Player player;
    private final Channel channel;

    public ServerUser(Channel channel) {
        this.channel = channel;
    }

    public void handle(IPacket packet) {
        packet.execute(this);
    }

    public Channel getChannel() { return this.channel; }

    public Optional<Player> getPlayer() { return Optional.ofNullable(this.player); }

    public boolean isLoggedIn() { return this.getPlayer().isPresent(); }

    public void loggedIn(String login) {
        this.player = new Player(login, this);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ServerUser that = (ServerUser) o;
        return Objects.equals(channel, that.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel);
    }
}
