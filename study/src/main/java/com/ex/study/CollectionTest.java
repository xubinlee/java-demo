package com.ex.study;

public class CollectionTest {
    public static void main(String[] args) {
        // LIST排序
//        List<Integer> list = new ArrayList<>();
//        list.add(3);
//        list.add(4);
//        list.add(1);
//        list.add(5);
//        System.out.println(list);
//        list.remove(1);
//        list.sort(Integer::compareTo);
//        System.out.println(list);
        // 转换
//        Object[] array = list.toArray();
//        List<Object> list1 = Arrays.asList(array);
//        List<Object> list2 = new ArrayList<>(list1);
//        list2.add(7);
//        System.out.println(list2);
//        System.out.println(list1);
        //去除字符串前后指定字符
//        String s="[004]0[500]";
//        s = StringUtils.strip(s, "[]");
////        s= StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(s,'0'), '0');
//        System.out.println(s);
//        // Map<String, List<UserInfo>>
//        ArrayListMultimap<String, UserInfo> multimap = ArrayListMultimap.create();
//        multimap.put("a",new UserInfo());
//        multimap.put("b",new UserInfo());
//        List<UserInfo> userInfos = multimap.get("b");
//        System.out.println(multimap);
//        // aa-bb-cc
//        List<String> list = new ArrayList<String>();
//        list.add("aa");
//        list.add("bb");
//        list.add("cc");
//        String join = StringUtils.join(list, "-");
//        System.out.println(join);
//        String join1 = Joiner.on('-').join(list);
//        System.out.println(join1);
//        // xiaohong*13-xiaoming*12
//        Map<String, Integer> map = Maps.newHashMap();
//        map.put("xiaoming", 12);
//        map.put("xiaohong",13);
//        System.out.println(map);
//        String join = Joiner.on('-').withKeyValueSeparator('*').join(map);
//        System.out.println(join);
        // 拆分转list
//        String str="1-2-3-4- 5-  6  ";
//        List<String> s = Splitter.on('-').omitEmptyStrings().trimResults().splitToList(str);
//        System.out.println(s);
        // list排序
//        List<String> list = new ArrayList<>();
//        list.add("aa");
//        list.add("dd");
//        list.add("cc");
//        Collections.sort(list);
//        System.out.println(list);
        // map排序
//        Map<String, Object> map = new HashMap<>();
//        map.put("a",new UserInfo());
//        map.put("e","1");
//        map.put("b",2);
//        map.put("c",40);
//        Map<String,Integer> phone=new HashMap();
//        phone.put("Apple",7299);
//        phone.put("SAMSUNG",6000);
//        phone.put("Meizu",2698);
//        phone.put("Xiaomi",2400);
//        phone.forEach((key,value)-> System.out.println(key+" "+value));
//        System.out.println("===================");
//        phone.keySet().stream().sorted().forEach((key)-> System.out.println(key+" "+phone.get(key)));
//        System.out.println("===================");
//        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(phone.entrySet());
//        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o1.getValue().compareTo(o2.getValue());
//            }
//        });
//        entries.stream().forEach(item-> System.out.println(item.getKey()+" "+item.getValue()));
    }
}
