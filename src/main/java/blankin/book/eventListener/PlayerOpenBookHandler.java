package blankin.book.eventListener;

import blankin.book.Constants;
import blankin.book.util.LockBookUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerOpenBookHandler implements Listener {

  @EventHandler
  private void playerBookPermissionCheck(PlayerInteractEvent event) {
    var player = event.getPlayer();
    if (event.getAction().isLeftClick()) {
      return;
    } else if (LockBookUtil.playerHasBookPermission(player, event.getItem())) {
      return;
    }

    player.closeInventory();
    event.setCancelled(true);
    player.sendActionBar(Component.text(Constants.RED_PREFIX + "이 책은 잠겨있어 열어 볼 수 없습니다"));
  }

}
