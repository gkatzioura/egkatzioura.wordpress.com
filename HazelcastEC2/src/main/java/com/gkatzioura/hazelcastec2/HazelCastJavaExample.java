package com.gkatzioura.hazelcastec2;

import com.hazelcast.aws.AWSClient;
import com.hazelcast.config.AwsConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by gkatzioura on 7/25/16.
 */
public class HazelCastJavaExample {

    public static void main(String args[]) {

        Config config = new Config();

        GroupConfig groupConfig = new GroupConfig();
        groupConfig.setName("ec2-group");
        groupConfig.setPassword("ec2-password");

        config.setGroupConfig(groupConfig);

        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getTcpIpConfig().setEnabled(false);
        joinConfig.getMulticastConfig().setEnabled(false);

        AwsConfig awsConfig = joinConfig.getAwsConfig();
        awsConfig.setIamRole("my-ec2-role");
        awsConfig.setEnabled(true);
        awsConfig.setRegion("eu-west-1");

        Hazelcast.newHazelcastInstance(config);
    }

}
