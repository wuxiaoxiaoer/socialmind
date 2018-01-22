package com.sicdlib.util.SNAUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DeMH on 2017/4/24.
 */
public class SNAUtil {

    //节点和下标的map映射,用来快速定位节点在矩阵中的下标,<nodeID,index>
    static Map<Object, Integer> nodexIndex = new HashMap<Object, Integer>();
    //用下标定位节点ID<index,nodeID>
    static Map<Integer, Object> indexNode = new HashMap<Integer, Object>();
    //存放每个ID和对应一系列分析指标所在map的映射
    //static Map<Object, Map<String, Object>> nodeSNAList = new HashMap<Object, Map<String, Object>>();
    //图的指标
    //static Map<String, Object> graphSNA = new HashMap<String, Object>();

    //这里都假设是有向图
    public static Map<String, Object> SNA(List<Map<String, Object>> nodes, List<Map<String, Object>> edges){


        //存放每个ID和对应一系列分析指标所在map的映射
         Map<Object, Map<String, Object>> nodeSNAList = new HashMap<Object, Map<String, Object>>();
        //图的指标
         Map<String, Object> graphSNA = new HashMap<String, Object>();

        int [][] adjMatrix=genergteMatrix(nodes,edges); //先转邻接矩阵
        int n = adjMatrix.length;     //点的数量
        int outdegree = 0;          //出度
        int indegree = 0;           //入度
        double relativeCentrality = 0;      //相对中心度
        int aCloseCentrality= 0;            //绝对接近中心度
        double rCloseCentrality=0;          //相对接近中心度
        double dc=0;
        double Cc =0;
        double correlationDegree = 0;       //关联度
        double rankDegree= 0;               //等级度

        Map<String, int[][]> floyd = Floyd(adjMatrix);  //floyd算法求得最短路径
        int[][] A = floyd.get("A");                     //最短路径矩阵
        int[][] path = floyd.get("path");               //最短路径
        int[][] t = TransitiveClosure(adjMatrix);       //可达矩阵

        //计算中心度
        int maxDegree=0; //用来记录最大的度。计算度数中心势
        double maxC = 0;    //绝对接近中心度的最大值
        int diameter = 0;//直径
        double ave = 0;//平均长度

        int sum = 0; //节点对的距离之和
        //计算关联度，统计
        int num = 0;//不可达点数量
        int num1 = 0;//可达的点的对数
        int num2 = 0;//相互可达的点的对数（2倍，算了两遍）
        int x=0;

        for (int i = 0; i < n; i++) {
            outdegree = 0;
            indegree = 0;
            aCloseCentrality=0;
            x=0;
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] == 1)
                    outdegree = outdegree + 1;//出度
                if (adjMatrix[j][i] == 1)
                    indegree = indegree + 1;//入度
                if (diameter < A[i][j])
                    diameter = A[i][j];//直径
                //这几个是计算关联度，等级度的参数
                if (0 == t[j][i]) x = x + 1;
                if (1 == t[i][j]) num1 = num1 + 1;
                if (1 == t[i][j] && 1 == t[j][i]) num2 = num2 + 1;//计算相互可达的点的对数，此时算了两遍，所以应该除以2；

                aCloseCentrality = aCloseCentrality + A[i][j]; //
                sum = sum + A[i][j];

            }

            if(maxDegree<(outdegree+indegree)) maxDegree=outdegree+indegree;//找到最大的度（这里是有向图，出度加入度）
            //if (aCloseCentrality > maxC) maxC = aCloseCentrality;//找到最大的绝对接近中心度
            if (x == n) num = num + 1;//这里 是统计不可达点的数目
            relativeCentrality =(double)(outdegree + indegree) / (2 * n - 2);       //相对中心度
            relativeCentrality =(double)Math.round(relativeCentrality*10000)/10000; //保留4位小数
            rCloseCentrality= (double)aCloseCentrality / (n - 1);              //相对接近中心度
            if (rCloseCentrality > maxC) maxC = rCloseCentrality;              //找到最大的相对接近中心度
            rCloseCentrality=(double)Math.round( rCloseCentrality*10000)/10000; //保留4位小数

            //存指定节点节点各种指标的映射
            Map<String, Object> nodeSNA = new HashMap<String, Object>();

            nodeSNA.put("outdegree", outdegree);    //出度
            nodeSNA.put("indegree", indegree);      //入度
            nodeSNA.put("relativeCentrality", relativeCentrality);      //相对中心度
            nodeSNA.put("aCloseCentrality", aCloseCentrality);          //绝对接近中心度
            nodeSNA.put("rCloseCentrality",rCloseCentrality);           //相对接近中心度
            nodeSNAList.put(indexNode.get(i), nodeSNA);         //indexNode.get(i);通过index找到对应的nodeID

        }

        //计算中心势
        int differenceSum=0;//度数中心势的差值和
        double add = 0;
        for (int i = 0; i <n; i++){

            int out=(Integer) nodeSNAList.get(indexNode.get(i)).get("outdegree");
            int in =(Integer) nodeSNAList.get(indexNode.get(i)).get("indegree");
            differenceSum=differenceSum+(maxDegree-(in+out));
           // add = add + ((double)maxC / (n - 1) - (double) nodeSNAList.get(indexNode.get(i)).get("rCloseCentrality"));
            add = add + (maxC  - (double)nodeSNAList.get(indexNode.get(i)).get("rCloseCentrality"));

        }

        dc=(double)differenceSum/(n*n-3*n+2);               //度数中心势
        dc=(double)Math.round(dc*10000)/10000;              //保留4位小数
        Cc = add / ((n - 1) * (n - 2) * (2 * n - 3));       //接近中心势
        Cc=(double)Math.round(Cc*10000)/10000;              //保留4位小数
        correlationDegree = (double)1 - ((double)num / (n* (n - 1) / 2));        //关联度计算公式
        correlationDegree=(double)Math.round(correlationDegree*10000)/10000;     //保留4位小数
        rankDegree = (double)1 - ((double)num2 / 2 / num1);        //等级度计算公式
        rankDegree=(double)Math.round(rankDegree*10000)/10000;     //保留4位小数

        graphSNA.put("degreeCentralization",dc);             //度数中心势
        graphSNA.put("closeCentralization", Cc);             //接近中心势
        //getScale(n);                                         //图规模
        //getDensity(n,edges.size());

        graphSNA.put("scale", getScale(n));//规模
        graphSNA.put("density", getDensity(n,edges.size()));//图密度
        graphSNA.put("diameter", diameter);//直径
        ave=(double)sum / (n*n);
        ave=(double)Math.round(ave*10000)/10000;//保留4位小数
        graphSNA.put("averageength", ave);         //平均长度
        graphSNA.put("correlationDegree", correlationDegree);   //关联度
        graphSNA.put("rankDegree", rankDegree);                 //等级度

        Map<String, Object> SNA =new HashMap<String, Object>();
        SNA.put("graphSNA",graphSNA);
        SNA.put("nodeSNAList",nodeSNAList);
        return SNA;
    }



    /**
     * 转为邻接矩阵，有边1，无边0
     */

    public static int[][] genergteMatrix(List<Map<String, Object>> nodes, List<Map<String, Object>> edges) {

        int[][] matrix = new int[nodes.size()][nodes.size()]; //定义了二维数组并初始化，邻接矩阵

        for (int i = 0; i < nodes.size(); i++) {
            nodexIndex.put(nodes.get(i).get("id"), i);//节点和下标的映射
            indexNode.put(i, nodes.get(i).get("id"));
            for (int j = 0; j < nodes.size(); j++) {

                matrix[i][j] = 0;//初始化矩阵

            }
        }
        //扫描边表，填写邻接矩阵。
        int Xindex = 0;
        int Yindex = 0;
        for (int i = 0; i < edges.size(); i++) {

            Object source = edges.get(i).get("source");//从边表得到第i条边的source和target
            Object target = edges.get(i).get("target");
            Xindex = nodexIndex.get(source);//获得这个节点的坐标,矩阵的横坐标
            Yindex = nodexIndex.get(target);//获得这个节点的坐标，矩阵的纵坐标
            matrix[Xindex][Yindex] = 1;//有边用1表示

        }
        return matrix;
    }

    /**
     * 使用Floyd算法计算出两点间的最短距离与路径
     */
    public static Map<String, int[][]> Floyd(int[][] matrix) {
        int[][] A = new int[matrix.length][matrix.length];
        int[][] path = new int[matrix.length][matrix.length];
        int i, j, k, n = matrix.length;

        //初始化矩阵
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                A[i][j] = matrix[i][j];
                path[i][j] = -1;
            }
        }
        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++){


                    if (A[i][j] > (A[i][k] + A[k][j]) && (A[i][k] + A[k][j])!=0) {     //&& (A[i][k] + A[k][j])!=0是因为邻接矩阵用0表示不可达
                        A[i][j] = A[i][k] + A[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }


        Map<String, int[][]> floyd = new HashMap<String, int[][]>();
        floyd.put("A", A);//两点间最短距离矩阵
        floyd.put("path", path);//两点间最短距离路径矩阵

        return floyd;

    }


    /**
     * 计算图的密度
     */
    public static double getDensity(int nNum, int eNum) {

        double dens = eNum / (nNum * (nNum - 1));
        //graphSNA.put("density", dens);//密度
         return dens;
    }


    /**
     * 计算图的规模
     * 节点数
     */
    public static int getScale(int nNum) {

        int scale = nNum;
        return scale;
        //graphSNA.put("scale", scale);//规模

    }


    /**
     * 利用可达矩阵
     * 邻接矩阵转可达矩阵
     */

    public static int[][] TransitiveClosure(int[][] matrix) {

        //可达矩阵
        int[][] t = new int[matrix.length][matrix.length];

        //初始化
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                t[i][j] = 0;

        for (int i = 0; i < matrix.length; i++)//进行遍历
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1)//发现可达
                    t[i][j] = 1;
                else
                    t[i][j] = 0;
            }
        for (int k = 0; k < matrix.length; k++)
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix.length; j++)

                    t[i][j] = t[i][j] | (t[i][k] & t[k][j]);//由文中公式可得，t是可达矩阵

        return t;

    }


   //##########################################################################################








}







