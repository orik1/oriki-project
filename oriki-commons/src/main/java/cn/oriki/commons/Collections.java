package cn.oriki.commons;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author oriki
 */
public final class Collections {

    private Collections() {
    }

    public static boolean isNonNullAndHasElements(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return !isNonNullAndHasElements(collection);
    }

    /**
     * 将 list 对象拆分为 n 个大小基本相同的 list
     * 注意：返回值为视图集合，不要修改视图内容
     *
     * @param list list
     * @param n    分割的数量
     * @param <T>  范型
     * @return 分割后的视图集合
     */
    public static <T> List<List<T>> averageAssign(List<T> list, int n) {
        // check
        Preconditions.checkArgument(n > 0);
        Preconditions.checkNotNull(list);
        Preconditions.checkArgument(!list.isEmpty());

        int listSize = list.size();

        List<List<T>> result = new ArrayList<>(n >= listSize ? list.size() : n);
        int remainder = listSize % n;
        int quotient = listSize / n;

        int offset = 0;
        for (int i = 0; i < n; i++) {
            List<T> value;
            int start = i * quotient + offset;
            int end = (i + 1) * quotient + offset;
            if (remainder > 0) {
                value = list.subList(start, end + 1);
                remainder--;
                offset++;
            } else {
                value = list.subList(start, end);
            }
            if (isNonNullAndHasElements(value)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * 将 list 按照每 n 个数据一个 list 分割
     *
     * @param list list 集合
     * @param n    每个集合的最大数据个数
     * @param <T>  范型
     * @return 分割后的视图集合
     */
    public static <T> List<List<T>> partition(List<T> list, int n) {
        if (null == list) {
            return java.util.Collections.emptyList();
        }
        return Lists.partition(list, n);
    }

}
