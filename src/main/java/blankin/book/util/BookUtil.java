package blankin.book.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BookUtil {

  public static boolean isWrittenBook(ItemStack item) {
    return item != null && Material.WRITTEN_BOOK.equals(item.getType());
  }

  public static boolean isWritableBook(ItemStack item) {
    return item != null && Material.WRITABLE_BOOK.equals(item.getType());
  }

  public static boolean isEditableBook(ItemStack item) {
    return isWritableBook(item) || isWrittenBook(item);
  }

}
