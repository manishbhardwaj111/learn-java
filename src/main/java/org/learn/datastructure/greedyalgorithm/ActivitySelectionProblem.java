package org.learn.datastructure.greedyalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActivitySelectionProblem {

    public record Activity(String name, int startTime, int endtime){
        @Override
        public String toString() {
            return name + "[" + startTime + "->" + endtime + "] ";
        }
    }

    public static List<Activity> maxActivity(List<Activity> activityList) {
        if (activityList == null || activityList.isEmpty()) {
            return Collections.emptyList();
        }
        activityList = activityList.stream().sorted(Comparator.comparingInt(a -> a.endtime)).toList();
        int endTime = 0;
        var selectedActivity = new ArrayList<Activity>();
        for (var activity: activityList) {
            if (activity.startTime >= endTime) {
                selectedActivity.add(activity);
                endTime = activity.endtime;
            }
        }
        System.out.println("Selected activity size is : " + selectedActivity.size());
        return selectedActivity;
    }

}
