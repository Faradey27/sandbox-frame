/*
 * Copyright 2014-2015 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaaproject.kaa.sandbox.web.servlet;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetLogsServlet extends HttpServlet {

    private static final String LOG_DIR = "/var/log/kaa/";
    private static final String ARCHIVE_NAME = "sandbox-logs.tar.gz";

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(GetLogsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Path path = Paths.get(LOG_DIR);
        if (Files.exists(path)) {
            try { TarArchiveOutputStream out = new TarArchiveOutputStream( new GzipCompressorOutputStream( new BufferedOutputStream( response.getOutputStream())));
                addFileToTarGz(out, LOG_DIR, "");
                response.setContentType("application/x-gzip");
                response.setHeader("Content-Disposition", "attachment;filename=" + ARCHIVE_NAME);
                out.flush();
            } catch (Exception ex) {
                logger.error("Unexpected error in GetLogsServlet.doGet: ", ex);
                throw new RuntimeException(ex);
            }
        } else {
            logger.error("");
            throw new RuntimeException("");
        }
    }

    private void addFileToTarGz(TarArchiveOutputStream out, String path, String base) throws IOException {
        File file = new File(path);
        String entryName = base + file.getName();
        TarArchiveEntry tarEntry = new TarArchiveEntry(file, entryName);

        out.putArchiveEntry(tarEntry);

        if (file.isFile()) {
            IOUtils.copy(new FileInputStream(file), out);
            out.closeArchiveEntry();
        } else {
            out.closeArchiveEntry();

            File[] children = file.listFiles();

            if (children != null) {
                for (File child : children) {
                    addFileToTarGz(out, child.getAbsolutePath(), entryName + "/");
                }
            }
        }
    }
}
