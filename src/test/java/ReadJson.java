import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/11 10:16
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/11 10:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ReadJson {
    public static String ReadJson(String path){
        //从给定位置获取文件
        File file = new File(path);
        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭文件流
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }

    public static void main(String[] args) {
//        String policy = ReadJson("E:/policy/bucket_read_write_policy.txt");
//        System.out.println(policy);
        String a="";
        System.out.println(StringUtils.isBlank(a));
    }
}
