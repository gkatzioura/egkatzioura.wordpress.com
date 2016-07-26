package com.gkatzioura.hazelcastec2;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;

/**
 * Created by gkatzioura on 7/26/16.
 */
public class HazelCastXMLExample {

    public static void main(String args[]) {

        Config config = new ClasspathXmlConfig("hazelcast.xml");

        Hazelcast.newHazelcastInstance(config);
    }

}
