package tp.ms.base.rest.generator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


public class MsGeneratorAppVOMapper {
	
    public static void main( String[] args ) throws XMLParserException, Exception {
    	gen("generator");
        System.out.println( "generator Done!" );
    }
    
    @SuppressWarnings("deprecation")
	public static void gen(String project) throws Exception, XMLParserException {
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(project + "/generatorConfig.xml");
//        File configFile = new File("/work/codebase/yibuyiliao/mybatis-gen/src/resource/platform.xml");
        
        try {
            gen(input);
        } finally {
            IOUtils.closeQuietly(input);
        }
        
        System.out.println("DaoGenerator> Done.");
    }

    private static void gen(InputStream input) throws IOException,
            XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(input);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
    
}
