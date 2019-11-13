package org.linghuxiong.spring.batch.admin.util;

/**
 * @author linghuxiong
 * @date 2019/11/5 10:57 下午
 */
public class PropertyNamingStrategyCamel {
    public static String toCamel(String str) {
        // 字符串缓冲区
        StringBuffer sbf = new StringBuffer();
        // 如果字符串包含 下划线
        if (str.contains("_")){
            // 按下划线来切割字符串为数组
            String[] split = str.split("_");
            // 循环数组操作其中的字符串
            for (int i = 0, index = split.length; i < index; i++)
            {
                if(i == 0){
                    sbf.append(split[i]);
                }else{
                    // 递归调用本方法
                    String upperTable = upperFirstCase(split[i]);
                    // 添加到字符串缓冲区
                    sbf.append(upperTable);
                }
            }
            return sbf.toString();
        } else {// 字符串不包含下划线
           return str;
        }
    }

    private static String upperFirstCase(String str){
        // 字符串缓冲区
        StringBuffer sbf = new StringBuffer();
        // 转换成字符数组
        char[] ch = str.toCharArray();
        // 判断首字母是否是字母
        if (ch[0] >= 'a' && ch[0] <= 'z'){
            // 利用ASCII码实现大写
            ch[0] = (char) (ch[0] - 32);
        }
        // 添加进字符串缓存区
        sbf.append(ch);
        return sbf.toString();
    }
}
