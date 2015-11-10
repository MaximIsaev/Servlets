package configuration_interface;


public class SourceConfig {

    private String newsFeedFileXmlUrlPath = "http://info.epam.com/info/rss.builder.feedrss.builder.feed?language=en&news=true&channels=official&channels=production&channels=career&channels=apps&channels=articles&channels=nowork&globalEvents=true&localEvents=true&my_unit=true&communities=AEI&communities=astana&communities=bicc&communities=big-data&communities=brest&communities=canada&communities=cdp-apac&communities=cdp-belarus&communities=cdphu&communities=cdpkzam&communities=cdpna&communities=cdp-russia&communities=cdp-ukraine&communities=cto&communities=cmcc&communities=russia&communities=contentcc&communities=cmsp&communities=dance-club-minsk&communities=dep&communities=dnipropetrovsk&communities=DrivingClubMinsk&communities=ecsp&communities=crm&communities=poland&communities=usa&communities=EACC&communities=epam-ic-center&communities=expedia&communities=global-talent-acquisition&communities=gomel&communities=grodno&communities=HRCommunityBY&communities=itservices&communities=im&communities=izhevsk&communities=javacc&communities=kharkiv&communities=kyiv&communities=llpd&communities=lviv&communities=MSFTCC&communities=manager-s-adviser&communities=ManagersCommunity&communities=Miniqs&communities=minsk&communities=mobilecc&communities=mogilev&communities=moscow&communities=PHP-ST&communities=qa-community&communities=rdua&communities=RMCommunity&communities=ryazan&communities=sapcc&communities=sap-solution-practice&communities=std&communities=samara&communities=saratov&communities=sposad&communities=sportportal-minsk&communities=st-petersburg&communities=strategic-alliances-and-partners0&communities=szeged&communities=tr-cbu-community&communities=tm&communities=TheNewsClub&communities=travelsolutions&communities=tver&communities=vinnitsa&communities=vitebsk&communities=zurich&albums=true&benefits=true&benefitCountry=russia&benefitCity=tver&benefitCategory=Health&benefitCategory=Family_Care&benefitCategory=Sport_Fitness&benefitCategory=Office_and_Home&benefitCategory=Education&benefitCategory=Food&benefitCategory=Auto&benefitCategory=Beauty&benefitCategory=Travel&benefitCategory=Entertainment&benefitCategory=Other&benefitCategory=corporate_programs";
    private String newsSourceFilePath = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\NewsStorage\\";
    private String outputFeedFilePath = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\NewsStorage\\NewsFeed.xml";

    public SourceConfig(String newsSourceFileName) {
        newsSourceFilePath = newsSourceFilePath + newsSourceFileName + ".xml";
    }

    public String getOutputFeedFilePath() {
        return outputFeedFilePath;
    }

    public void setOutputFeedFilePath(String outputFeedFilePath) {
        this.outputFeedFilePath = outputFeedFilePath;
    }

    public String getNewsFeedFileXmlUrlPath() {
        return newsFeedFileXmlUrlPath;
    }

    public void setNewsFeedFileXmlUrlPath(String newsFeedFileXmlUrlPath) {
        this.newsFeedFileXmlUrlPath = newsFeedFileXmlUrlPath;
    }
}
