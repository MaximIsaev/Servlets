package com.get_news_feed_file;


import configuration.sources.SourceConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadNewsFeedFile {

    private String newsFeedsUrl;
    private String outputFeedFilePath;

    private File outputFeedFolder;
    private File outputFeedFile;

    public DownloadNewsFeedFile(SourceConfig sourceConfig) {
        this.newsFeedsUrl = sourceConfig.getNewsFeedFileXmlUrlPath();
        this.outputFeedFilePath = sourceConfig.getOutputFeedFilePath();
        outputFeedFolder = new File(sourceConfig.getOutputFeedFolderPath());
        outputFeedFile = new File(sourceConfig.getOutputFeedFilePath());
    }

    public String download() {
        try {
            URL connection = new URL(newsFeedsUrl);
            ReadableByteChannel rbc = Channels.newChannel(connection.openStream());
            validateOutputFeedFile();
            write(rbc, outputFeedFilePath);
        } catch (IOException e) {
            System.out.println(e);
        }
        return outputFeedFilePath;
    }

    public String getNewsFeedsUrl() {
        return newsFeedsUrl;
    }

    public String getOutputFeedFilePath() {
        return outputFeedFilePath;
    }

    public File getOutputFeedFile() {
        return outputFeedFile;
    }

    public void validateOutputFeedFile() throws IOException {

        if (outputFeedFolder.exists()) {
            createOutputFeedFile();
        } else {
            outputFeedFolder.mkdirs();
            createOutputFeedFile();
        }
    }

    public void write(ReadableByteChannel in, String strPath) {

        try {
            FileOutputStream fos = new FileOutputStream(strPath);
            fos.getChannel().transferFrom(in, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void createOutputFeedFile() throws IOException {

        if (!outputFeedFile.exists()) {
            outputFeedFile.createNewFile();
        }
    }
}
