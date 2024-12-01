package br.albatross.sysgarantia.persistence.migration;

//@Startup @Singleton
//@TransactionManagement(TransactionManagementType.BEAN)
public class FlywayDBMigration {

//    @Resource(lookup = "java:jboss/datasources/ProblemaDS")
//    private DataSource dataSource;

//    @PostConstruct
    public void migrate() {
//        Flyway
//            .configure()
//            .dataSource(dataSource)
//            .locations("classpath:/flyway/db/migration")
/*
        O código comentado pode ser utilizado para implementar o Flyway em um ambiente em que já existe o schema criado.
        Substitua o número da versão e o description de acordo com o último arquivo de migration.            
            .baselineVersion("8")
            .baselineDescription("Insert-into-Problema-and-DescricaoProblema")
            .baselineOnMigrate(true)
*/
//            .load()
//            .migrate();

    }

}
