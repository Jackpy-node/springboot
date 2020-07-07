import cn.sunline.ltts.web.main.StartWebServer;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: PACKAGE_NAME
 * @data: 2020-6-19 22:42
 * @discription:
 **/
public class TestBoot {

    public static void main(String[] args) {

        String vmid = "app1";
        if (args != null && args.length == 1) {
            vmid = args[0];
        }

        System.setProperty("ltts.vmid", vmid);
        System.setProperty("config.id", vmid);
        if (System.getProperty("setting.file") == null) {
            System.setProperty("setting.file", "setting.dev_web.properties");
        }

        String logFile = System.getProperty("log4j.configurationFile");
        if (logFile == null || logFile.trim().length() == 0) {
            System.setProperty("log4j.configurationFile", "ltts_log_dev.xml");
        }

        System.setProperty("rmb.dcn.no", "A00");
        System.setProperty("rmb.system.id", "3009");
        System.setProperty("ltts.home", System.getProperty("user.dir"));
        System.setProperty("ltts.log.home", System.getProperty("user.dir"));

        StartWebServer.startUp();

    }

}
