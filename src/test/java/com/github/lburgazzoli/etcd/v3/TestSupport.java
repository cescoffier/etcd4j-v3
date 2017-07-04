/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.etcd.v3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class TestSupport {
    public static File loadCert(String name) throws IOException {
        File tmpFile = File.createTempFile(name, "");
        tmpFile.deleteOnExit();

        InputStream in = new BufferedInputStream(TestSupport.class.getResourceAsStream("/certs/" + name));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(tmpFile));

        try {
            int b;
            while ((b = in.read()) != -1) {
                os.write(b);
            }
            os.flush();
        } finally {
            in.close();
            os.close();
        }

        return tmpFile;
    }

    public static X509Certificate loadX509Cert(String fileName) throws CertificateException, IOException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        try ( InputStream in = TestSupport.class.getResourceAsStream("/certs/" + fileName)) {
            return (X509Certificate) cf.generateCertificate(in);
        }
    }
}
