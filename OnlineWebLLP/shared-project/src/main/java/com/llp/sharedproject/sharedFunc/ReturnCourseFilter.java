package com.llp.sharedproject.sharedFunc;

import java.util.ArrayList;
import java.util.List;

public class ReturnCourseFilter {
    public static double returnRatingFilter(int choice){
        switch (choice){
            case 1:
                return 4.5;
            case 2:
                return 4.0;
            case 3:
                return 3.5;
            case 4:
                return 3.0;
            default: return 0;
        }
    }
    public static List<Integer> returnDurationFilter(int choice){
        List<Integer> result = new ArrayList<>();
        switch (choice){
            case 1:
                result.add(0);
                result.add(60);
                break;
            case 2:
                result.add(60);
                result.add(180);
                break;
            case 3:
                result.add(180);
                result.add(360);
                break;
            case 4:
                result.add(360);
                result.add(1020);
                break;
            case 5:
                result.add(1020);
                result.add(null);
            default:
                break;
        }
        return result;
    }

}
