package com.lcyanxi.controller;

import com.google.common.collect.Lists;
import com.lcyanxi.model.UserClassDTO;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author lichang
 * @date 2021/3/5
 */
public class ExcelTest {
    public static void main(String[] args) throws Exception {

        String path = "/Users/koolearn/Desktop/shayu.csv";
        String path2 = "/Users/koolearn/Desktop/banke.csv";

        List<UserClassDTO> shayu = getData(path);
        List<UserClassDTO> banke = getData(path2);

        shayu.forEach(item->{
            boolean flag = false;
            for (UserClassDTO dto : banke){
                if ((item.getOrderNo().equals(dto.getOrderNo()) &&
                        item.getProductId() .equals(dto.getProductId())  &&
                        item.getStatus().equals(dto.getStatus()))){
                    flag = true;
                    break;
                }
            }
            if (flag){
                System.out.println(item.getOrderNo()+ "," +item.getProductId() +","+item.getStatus());
            }
        });
        return ;
    }


    private static List<UserClassDTO> getData(String path) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(path), "UTF-8"));
        String line ;
        String[] arrs ;
        List<UserClassDTO> dtos = Lists.newArrayList();
        while ((line = br.readLine()) != null) {
            arrs=line.split(",");
            UserClassDTO dto = new UserClassDTO();
            dto.setOrderNo(arrs[0]);
            dto.setProductId(Integer.parseInt(arrs[1]));
            dto.setStatus(Integer.parseInt(arrs[2]));
            dtos.add(dto);
        }
        br.close();
        return dtos;
    }
}
