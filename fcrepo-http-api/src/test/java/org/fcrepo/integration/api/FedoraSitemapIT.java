
package org.fcrepo.integration.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

public class FedoraSitemapIT extends AbstractResourceIT {

    @Test
    public void testGetSitemapIndex() throws Exception {

        getStatus(postObjMethod("fcr:new"));
        final HttpGet httpGet = new HttpGet(serverAddress + "sitemap");

        assertEquals(200, getStatus(httpGet));

        logger.info(IOUtils.toString(execute(httpGet).getEntity().getContent()));

    }

    @Test
    public void testGetSitemap() throws Exception {

        getStatus(postObjMethod("test:1"));
        getStatus(postObjMethod("fcr:new"));

        final HttpGet httpGet = new HttpGet(serverAddress + "sitemap/1");

        assertEquals(200, getStatus(httpGet));

        final String s = IOUtils.toString(execute(httpGet).getEntity().getContent());

        logger.info("Got sitemap response: {}", s);
        assertTrue(s.contains("/rest/objects/test:1</loc>"));


    }

}
