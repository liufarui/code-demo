package com.github.liufarui.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liufarui
 * @date 2023/5/31 23:34
 */
public class SentinelTest {

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //资源名称
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(10);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        // 配置规则.
        initFlowRules();

        for (int i = 0; i < 20; i++) {
            //设置被保护的资源
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("hello world " + i);
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                System.out.println("blocked " + i);
            }
        }
    }

}
