package seleniumUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

        private static final String FILE_DIR = System.getProperty("user.dir");
        private static final String FILE_PATH = "/src/main/config";

        private static final Logger LOG = LoggerFactory.getLogger("FileUtility");

        private static Properties p = new Properties();

        private static String env = "";

        /**
         * This method returns the property value of file present in config folder.
         *
         * @param  file is the name of the file which is present in config folder
         * @param  property is the property name which needs to read from file.
         *
         * @return returns property value after reading from file
         */
        public static String readProperty(String file, String property){
            try {
                if(file.equalsIgnoreCase(DriverScript.ENV)) {
                    p.load(new FileInputStream(new File(FILE_DIR+FILE_PATH+"/"+DriverScript.ENV+"/instances.properties")));
                }
                else {
                    p.load(new FileInputStream(new File(FILE_DIR+FILE_PATH+"/common/webdriver.properties")));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                LOG.info("File is not found in the path --->" + FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
                LOG.info("IOException at --->" + FILE_DIR+FILE_PATH);
            }
            return p.getProperty(property);
        }


        /**
         * This method returns the property value for environment variable
         *
         * @return env, the environment to test
         */
        public static String testEnvironment() {
            try {
                p.load(new FileInputStream(new File(FILE_DIR+FILE_PATH+"/environment/env.properties")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                LOG.info("File is not found in the path --->" + FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
                LOG.info("IOException at --->" + FILE_DIR+FILE_PATH);
            }
            env = p.getProperty("test_environment");
            return env;
        }
    }

