package pokemane.northernrail.common;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by pokemane on 2/15/14.
 */
public class NRConfig {

    /**
     * The version of Northern Rail.
     */
    public static final int MAJOR_VERSION = 0;
    public static final int MINOR_VERSION = 2;
    public static final int REVISION_VERSION = 15;
    public static final char STATUS_VERSION = 'X'; // a/alpha, b/beta, f/final, x/experimental
    public static final String BUGFIX_VERSION = "9";
    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + STATUS_VERSION + BUGFIX_VERSION;
    public static final int WORLDGEN_VERSION = 1; // Bump this when changing world generation so the world regens

    /**
     * The Northern Rail configuration file.
     */
    public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(), "NorthernRail/NorthernRail.cfg"));

    static
    {
        /**
         * Loads the configuration and sets all the values.
         */
        CONFIGURATION.load();
        CONFIGURATION.save();
    }
}
