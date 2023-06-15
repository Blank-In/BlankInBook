package blankin.book.util;

import blankin.book.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class LockBookUtil {

  private static final ObjectMapper objectMapper = new ObjectMapper();


  public static boolean playerHasBookPermission(Player player, ItemStack item) {
    if (player.isOp() || player.hasPermission(Constants.MASTER_PERMISSION)) {
      return true;
    }
    var playerUuid = player.getUniqueId().toString();
    var lockedUuidList = LockBookUtil.getLockedUuidList(item);

    if (lockedUuidList.isEmpty()) {
      return true;
    }
    for (var lockedUuid : lockedUuidList) {
      if (playerUuid.equals(lockedUuid)) {
        return true;
      }
    }
    return false;
  }


  public static List<String> getLockedUuidList(ItemStack item) {
    if (!BookUtil.isEditableBook(item)) {
      return Collections.emptyList();
    }

    var dataContainer = item.getItemMeta().getPersistentDataContainer();
    var lockedUuidData = dataContainer.getOrDefault(Constants.LOCK_BOOK_KEY, PersistentDataType.STRING, "[]");
    try {
      return objectMapper.readValue(lockedUuidData, List.class);
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public static void setLockedUuidList(ItemStack item, List<String> playerUuidList) {
    if (!BookUtil.isEditableBook(item)) {
      return;
    }

    var itemMeta = item.getItemMeta();
    var dataContainer = itemMeta.getPersistentDataContainer();
    try {
      var lockedUuidData = objectMapper.writeValueAsString(playerUuidList);
      dataContainer.set(Constants.LOCK_BOOK_KEY, PersistentDataType.STRING, lockedUuidData);
      setLockedLore(itemMeta, playerUuidList.isEmpty());
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    item.setItemMeta(itemMeta);
  }


  // TODO deprecated 된 lore 를 대체할 필요가 있다.
  public static void setLockedLore(ItemMeta itemMeta, boolean isRemoved) {
    if (isRemoved) {
      removeLockedLore(itemMeta);
    } else {
      addLockedLore(itemMeta);
    }
  }

  private static void addLockedLore(ItemMeta itemMeta) {
    if (itemMeta.hasLore()) {
      var itemLore = Objects.requireNonNull(itemMeta.getLore());
      if (!itemLore.contains(Constants.LOCK_BOOK_LORE)) {
        itemLore.add(Constants.LOCK_BOOK_LORE);
      }
      itemMeta.setLore(itemLore);
      return;
    }

    itemMeta.lore(List.of(Component.text(Constants.LOCK_BOOK_LORE)));
  }

  private static void removeLockedLore(ItemMeta itemMeta) {
    if (!itemMeta.hasLore()) {
      return;
    }
    var itemLore = Objects.requireNonNull(itemMeta.getLore());
    itemLore.removeIf(Constants.LOCK_BOOK_LORE::equals);
    itemMeta.setLore(itemLore);
  }

}
