package blankin.book;

import blankin.book.eventListener.PlayerOpenBookHandler;
import blankin.book.util.BookUtil;
import blankin.book.util.LockBookUtil;
import java.util.ArrayList;
import java.util.Collections;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class BlankInBook extends JavaPlugin {

  public void sendMessage(CommandSender sender, String Message) {
    sender.sendMessage(Message);
    getLogger().info(Message);
  }


  @Override
  public void onEnable() {
    var pluginManager = getServer().getPluginManager();
    pluginManager.registerEvents(new PlayerOpenBookHandler(), this);
    getLogger().warning(Constants.PLUGIN_TITLE + "작동 시작");
  }

  @Override
  public void onDisable() {
    saveConfig();
    getLogger().warning(Constants.PLUGIN_TITLE + "작동 중지");
  }


  // TODO commandHandler 로 분할해보자
  @Override
  public boolean onCommand(
      @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    var player = getServer().getPlayer(sender.getName());
    if (player == null) {
      return false;
    }
    var handItem = player.getInventory().getItemInMainHand();

    switch (command.getName().toLowerCase()) {
      case "blankinbook" -> {
        sendMessage(sender, Constants.PLUGIN_TITLE + "현재 플러그인 버전 " + Constants.PLUGIN_VERSION);
        return true;
      }
      case "책잠금" -> {
        if (!BookUtil.isEditableBook(handItem)) {
          player.sendActionBar(Component.text(Constants.RED_PREFIX + "수정이 가능한 책을 손에 들어주세요"));
          return true;
        } else if (!LockBookUtil.getLockedUuidList(handItem).isEmpty()) {
          player.sendActionBar(Component.text(Constants.RED_PREFIX + "이 책은 이미 잠금 되어 있습니다"));
          return true;
        }

        var playerUuidList = new ArrayList<String>();
        if (args.length == 0) {
          playerUuidList.add(player.getUniqueId().toString());
        }
        for (var playerName : args) {
          var playerUuid = getServer().getPlayerUniqueId(playerName);
          if (playerUuid != null) {
            playerUuidList.add(playerUuid.toString());
          }
        }

        LockBookUtil.setLockedUuidList(handItem, playerUuidList);
        player.sendActionBar(Component.text(Constants.GREEN_PREFIX + "책에 새로운 잠금을 설정하였습니다"));
      }
      case "책잠금해제" -> {
        if (!BookUtil.isEditableBook(handItem)) {
          player.sendActionBar(Component.text(Constants.RED_PREFIX + "수정이 가능한 책을 손에 들어주세요"));
          return true;
        } else if (!LockBookUtil.playerHasBookPermission(player, handItem)) {
          player.sendActionBar(Component.text(Constants.RED_PREFIX + "이 책의 잠금을 해제할 수 없습니다"));
          return true;
        }

        LockBookUtil.setLockedUuidList(handItem, Collections.emptyList());
        player.sendActionBar(Component.text(Constants.GREEN_PREFIX + "책의 잠금을 해제하였습니다."));
      }
      case "책다시쓰기" -> {
        if (!BookUtil.isWrittenBook(handItem)) {
          player.sendActionBar(Component.text(Constants.RED_PREFIX + "서명이 된 책을 손에 들어주세요"));
          return true;
        } else if (!LockBookUtil.playerHasBookPermission(player, handItem)) {
          player.sendActionBar(Component.text(Constants.RED_PREFIX + "이 책을 다시 쓸 수 없습니다"));
          return true;
        }
        var bookMeta = (BookMeta) handItem.getItemMeta();
        handItem.setType(Material.WRITABLE_BOOK);
        handItem.setItemMeta(bookMeta);
      }
    }

    return true;
  }

}