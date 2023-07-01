package blankin.book;

import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.DARK_RED;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.YELLOW;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;

public class Constants {

  public final static String PLUGIN_TITLE = BOLD.toString() + DARK_RED + "[" + DARK_GRAY + "BlankInBook" + DARK_RED + "] " + GRAY;
  public final static String PLUGIN_VERSION = "1.1.20230613"; // 버저닝 MAJOR.업데이트-횟수.릴리즈-일자

  public final static String GREEN_PREFIX = GRAY + "[" + YELLOW + " ! " + GRAY + "] " + GREEN;
  public final static String RED_PREFIX = GRAY + "[" + YELLOW + " ! " + GRAY + "] " + RED;
  public final static String YELLOW_PREFIX = GRAY + "[" + YELLOW + " ! " + GRAY + "] " + YELLOW;


  public final static String MASTER_PERMISSION = "BlankInBook.blankinbook";

  public final static NamespacedKey LOCK_BOOK_KEY = NamespacedKey.minecraft("blankinbook.lock_book");
  public final static String LOCK_BOOK_LORE = YELLOW_PREFIX + "잠금된 책";

}
