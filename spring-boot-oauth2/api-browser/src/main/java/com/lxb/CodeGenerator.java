package com.lxb.apibrowser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lxb.apibrowser.controller.base.BaseController;
import com.lxb.apibrowser.mapper.base.BaseMapper;
import com.lxb.apibrowser.service.base.BaseService;
import com.lxb.apibrowser.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

public class CodeGenerator {

    //输出目录
//    private String outputDir = System.getProperty("user.dir")+"/src/main/java";
    private String outputDir = "D:/workspace/ex/spring-boot-oauth2/api-browser/src/main/java";

    //模板包路径（classpath下）
    private String templatePath = "/generator/templates";

    //父包和子包设置
    private String parentPackage = "com.lxb.apibrowser";

    /**
     * 需要继承的基类设置
     */
    private String controllerPackage = "controller";
    private String servicePackage = "service";
    private String serviceImplPackage ="service.impl";
    private String mapperPackage = "mapper";
    private String mapperXmlPackage = "mapper.xml";
    private String entityPackage = "entity";

    /**
     * 需要继承的基类设置
     */
    private Class superController = BaseController.class;
    private Class superService = BaseService.class;
    private Class superServiceImpl = BaseServiceImpl.class;
    private Class superMapper = BaseMapper.class;
    private Class superEntity;

    /**
     * 数据源设置
     */
    private String dataSourceUrl = "jdbc:mysql://192.168.1.10:3306/wqb_yjz?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private String driverName = com.mysql.cj.jdbc.Driver.class.getName();
    private String username = "xw";
    private String password = "Xw9999";

    /**
     * 存在时是否覆盖，建议设置为false
     */
    private boolean overwrite = false;

    public static void main(String[] args) {
        new CodeGenerator()
//                .generate("User", "t_sys_user", IdType.UUID)
//                .generate("Account", "t_basic_account", IdType.UUID)
                .generate("Customer", "t_customer", IdType.UUID);
    }

    /**
     * @param entity 实体名称
     * @param table  表名
     */
    private CodeGenerator generate(String entity, String table, IdType idType) {
        if (StringUtils.isBlank(table)) {
            throw new RuntimeException("请填写表名");
        }

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig()
                .setOutputDir(outputDir)
                .setControllerName(entity + "Controller")
                .setServiceName(entity + "Service")
                .setServiceImplName(entity + "ServiceImpl")
                .setMapperName(entity + "Mapper")
                .setXmlName(entity + "Mapper")
                .setEntityName(entity)
                .setAuthor("lixubin")
                .setFileOverride(overwrite)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setIdType(idType)
                .setDateType(DateType.ONLY_DATE)
                .setOpen(false);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig()
                .setUrl(dataSourceUrl)
                .setSchemaName("public")
                .setDriverName(driverName)
                .setUsername(username)
                .setPassword(password);

        // 包配置
        PackageConfig pc = new PackageConfig()
                .setParent(parentPackage)
                .setController(controllerPackage)
                .setService(servicePackage)
                .setServiceImpl(serviceImplPackage)
                .setMapper(mapperPackage)
                .setXml(mapperXmlPackage)
                .setEntity(entityPackage);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig()
                .setInclude(table)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .setSuperControllerClass(superController == null ? null : superController.getName())
                .setSuperServiceClass(superService == null ? null : superService.getName())
                .setSuperServiceImplClass(superServiceImpl == null ? null : superServiceImpl.getName())
                .setSuperMapperClass(superMapper == null ? null : superMapper.getName())
                .setSuperEntityClass(superEntity == null ? null : superEntity.getName());

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig()
                .setController(templatePath + "/controller.java")
                .setService(templatePath + "/service.java")
                .setServiceImpl(templatePath + "/serviceImpl.java")
                .setEntity(templatePath + "/entity.java")
                .setMapper(templatePath + "/mapper.java")
                .setXml(templatePath + "/mapper.xml");

        // 代码生成器
        new AutoGenerator()
                .setDataSource(dsc)
                .setStrategy(strategy)
                .setPackageInfo(pc)
                .setGlobalConfig(globalConfig)
                .setTemplate(templateConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .execute();

        return this;
    }

    /**
     * 分组生成，生成的代码在配置的（包 + 组名）构成在二级包下
     *
     * @param name
     * @param generatorFunction
     * @return
     */
    public CodeGenerator group(String name, Consumer<CodeGenerator> generatorFunction) {
        setGroupName(name);
        generatorFunction.accept(this);
        // 删除组名不影响下一个生成
        unsetGroupName(name);
        return this;
    }

    /**
     * 设置组名
     *
     * @param name
     * @return
     */
    private CodeGenerator setGroupName(String name) {
        controllerPackage = addSuffix(controllerPackage, name);
        servicePackage = addSuffix(servicePackage, name);
        serviceImplPackage = addSuffix(serviceImplPackage, name);
        mapperPackage = addSuffix(mapperPackage, name);
        mapperXmlPackage = addSuffix(mapperXmlPackage, name);
        entityPackage = addSuffix(entityPackage, name);
        return this;
    }

    private CodeGenerator unsetGroupName(String name) {
        controllerPackage = removeSuffix(controllerPackage, name);
        servicePackage = removeSuffix(servicePackage, name);
        serviceImplPackage = removeSuffix(serviceImplPackage, name);
        mapperPackage = removeSuffix(mapperPackage, name);
        mapperXmlPackage = removeSuffix(mapperXmlPackage, name);
        entityPackage = removeSuffix(entityPackage, name);
        return this;
    }

    private String addSuffix(String parentPackage, String childPackage) {
        return parentPackage + (StringUtils.isNotBlank(childPackage) ? "." + childPackage : "");
    }

    private String removeSuffix(String parentPackage, String childPackage) {
        return StringUtils.replace(parentPackage, "." + childPackage, "");
    }
}
