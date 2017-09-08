package org.schedulex.schedule;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.schedulex.JobDescription;
import org.schedulex.JobEntry;
import org.schedulex.common.utils.DateUtil;
import org.schedulex.common.utils.StringUtil;
import org.schedulex.exceptions.SchedulexBasicException;
import org.schedulex.expression.CronExpressionEx;
import org.schedulex.schedule.graph.ScheduleGraph;

import java.util.Date;

/**
 * 任务排班信息
 */
@ToString
@Getter
@Setter
public class Schedule extends JobEntry{

    /**
     * 作业执行图
     */
    private ScheduleGraph scheduleGraph;


    private JobDescription description;


    private Date lastInvokeDate;

    private Date nextInvokeDate;

    private static final int INVOKE_DATE_STEP = 2;

    private boolean inquireInvoke(){

        String expression = this.description.getExpression();

        if(StringUtil.isNotEmpty(expression)){
            Date[] invokeDate = new Date[INVOKE_DATE_STEP];
            try {
                CronExpressionEx exp = new CronExpressionEx(expression);
                Date currentDate = DateUtil.getCurrentDate();

                for (int i = 1; i <= 2; i++)
                {
                    currentDate = exp.getNextValidTimeAfter(currentDate);
                    invokeDate[i] = currentDate;
                    currentDate = new java.util.Date(currentDate.getTime() + 1000);
                }

            }catch (Exception e){
                throw new SchedulexBasicException(e);
            }

            Date invokeDateGate = invokeDate[0];
            Date currentInvokeDate = DateUtil.parse(DateUtil.format(DateUtil.getCurrentDate(),DateUtil.PART_PATTERN),DateUtil.PART_PATTERN);

            if(invokeDateGate.getTime() == currentInvokeDate.getTime()){

                if(null != this.lastInvokeDate){
                    if(DateUtil.format(this.lastInvokeDate,DateUtil.PART_PATTERN).equals(DateUtil.format(invokeDateGate,DateUtil.PART_PATTERN))){
                        return false;
                    }else {
                        this.lastInvokeDate = invokeDateGate;
                        this.lastInvokeDate = invokeDate[1];
                        return true;
                    }
                }else {

                    this.lastInvokeDate = invokeDateGate;
                    this.lastInvokeDate = invokeDate[1];
                    return true;
                }
            }

        }
        return false;
    }



}
