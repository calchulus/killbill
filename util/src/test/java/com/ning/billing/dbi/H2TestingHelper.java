/*
 * Copyright 2010-2012 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.dbi;

import java.io.IOException;
import java.sql.SQLException;

import org.h2.tools.Server;
import org.testng.Assert;

public class H2TestingHelper extends DBTestingHelper {

    private Server server;

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            Assert.fail(e.toString());
        }
    }

    @Override
    public boolean isUsingLocalInstance() {
        return false;
    }

    @Override
    public String getConnectionString() {
        return "No connection string (in memory)";
    }

    @Override
    public String getJdbcConnectionString() {
        return "jdbc:h2:mem:" + DB_NAME + ";MODE=MYSQL;DB_CLOSE_DELAY=-1";
    }

    @Override
    public String getInformationSchemaJdbcConnectionString() {
        return "jdbc:h2:mem:foo;MODE=MYSQL;SCHEMA_SEARCH_PATH=INFORMATION_SCHEMA;DB_CLOSE_DELAY=-1";
    }

    @Override
    public void start() throws IOException {
        // Start a web server for debugging (http://127.0.0.1:8082/)
        try {
            server = Server.createWebServer(new String[]{}).start();
        } catch (SQLException e) {
            Assert.fail(e.toString());
        }
    }

    @Override
    public void stop() {
        server.stop();
    }
}
