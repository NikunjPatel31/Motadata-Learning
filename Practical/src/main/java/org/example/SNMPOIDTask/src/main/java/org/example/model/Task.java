package org.example.model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Task
{
    // oid - type - model
    private static final Map<String, Map<String, Set<String>>> snmpDevices = new HashMap<>();

    private static final Map<String, String> catalogType = new HashMap<>();

    private static final Map<String, String> vendorType = new HashMap<>();

    private static final JSONArray array = new JSONArray();


    public static String TYPE = "snmp.device.catalog.type";
    public static String MODEL = "snmp.device.catalog.model";
    public static String VENDOR = "snmp.device.catalog.vendor";
    public static String OID = "snmp.device.catalog.oid";

    private static final String SPLIT = "#";

    static
    {
        catalogType.put("Print", "Printer");
        catalogType.put("Layer 3 Switch", "Switch");
        catalogType.put("SANSwitch", "Switch");
        catalogType.put("UPS", "UPS");
        catalogType.put("Switch", "Switch");
        catalogType.put("Firewall", "Firewall");
        catalogType.put("LoadBalancer", "Load Balancer");
        catalogType.put("Router", "Router");

        vendorType.put("Huawei", "HUAWEI");
        vendorType.put("huawei", "HUAWEI");
        vendorType.put("Nokia", "Nokia Data Communications");
        vendorType.put("Ericsson", "Ericsson Wireless LAN Systems");
        vendorType.put("Teldat", "TELDAT S.A.");

    }

    public static void main(String[] args)
    {
        populateSNMPDevice();

        getNewOID();
    }

    private static void populateSNMPDevice()
    {

        AtomicInteger count = new AtomicInteger();

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("/home/nikunj/Downloads/Task/snmp-devices.json"))
        {
            Object obj = parser.parse(reader);

            JSONArray oidArray = (JSONArray) obj;

            oidArray.forEach(oid ->
            {
                JSONObject value = ((JSONObject) oid);

//                if (vendor.equals("TELDAT, S.A."))
//                {
//                    vendor = vendorType.get("Teldat");
//                }

                var oidNumber = value.get(OID).toString();

                var type = value.get(TYPE).toString();

                var model = value.get(MODEL).toString();

                var vendor = value.get(VENDOR).toString();


//                var device = value.get(OID).toString() + SPLIT +
//                        value.get(TYPE).toString()+ SPLIT  +
//                        value.get(MODEL).toString() + SPLIT +
//                        vendor;

                if (snmpDevices.containsKey(oid))
                {
                    // oid is there
                    var typeMap = snmpDevices.get(oid);

                    if (typeMap.containsKey(type))
                    {
                        // oid and type is there
                        var modelSet = typeMap.get(type);

                        modelSet.add(model);

                        var jsonObject = new JSONObject();

                        jsonObject.put(OID, oidNumber);
                        jsonObject.put(MODEL, model);
                        jsonObject.put(VENDOR, vendor);
                        jsonObject.put(TYPE, type);

                        array.add(jsonObject);
                    }
                } else
                {
                    // oid is not present
                    Map<String, Set<String>> typeMap = new HashMap<>();


                    Set<String> modelSet = new HashSet<>();
                    modelSet.add(model);

                    typeMap.put(type, modelSet);

                    snmpDevices.put(oidNumber, typeMap);

                    var jsonObject = new JSONObject();

                    jsonObject.put(OID, oidNumber);
                    jsonObject.put(MODEL, model);
                    jsonObject.put(VENDOR, vendor);
                    jsonObject.put(TYPE, type);

                    array.add(jsonObject);

                    count.incrementAndGet();

                }
//                if (!snmpDevices.contains(device))
//                {
//                    // device does not exits
//                    snmpDevices.add(device);
//                }
//                else
//                {
//                    // device already exits
////                    System.out.println(device);
//                }

//                if (value.get(VENDOR).toString().equals("TELDAT, S.A."))
//                {
//                    value.put(VENDOR, vendorType.get("Teldat"));
//                }
//
//                if (snmpDevices.containsKey(((JSONObject) oid).get(OID).toString()))
//                {
//                    System.out.println("DUPLICATE: " + ((JSONObject) oid).get(OID));
//                }
////                snmpDevices.computeIfAbsent(((JSONObject) oid).get(OID).toString(), key -> {
////                    return snmpDevices.put(((JSONObject) oid).get(OID).toString(), ((JSONObject) obj));
////                });
//
//
//
//
//                if (snmpDevicesV2.containsKey(((JSONObject) oid).get(OID).toString()))
//                {
//                    // now check type
//                    if (!snmpDevicesV2.get(((JSONObject) oid).get(OID).toString())
//                            .containsKey(((JSONObject) oid).get(TYPE).toString()))
//                    {
//                        // check does not match
//                        // insert new data
//                        var innerMap = snmpDevicesV2.get(((JSONObject) oid).get(OID).toString());
//
//                        innerMap.put(((JSONObject) oid).get(TYPE).toString(), value);
//                    }
//                }
//                else
//                {
//                    HashMap<String, JSONObject> innerMap = new HashMap<>();
//
//                    innerMap.put(((JSONObject) oid).get(TYPE).toString(), value);
//                    snmpDevicesV2.put(((JSONObject) oid).get(OID).toString(), innerMap);
//                }
//
//
//
//
//                snmpDevices.putIfAbsent(((JSONObject) oid).get(OID).toString(),
//                        value);
//
//                // adding all unique vendors to vendorsFromJSON
//                vendorsFromJSON.add(((JSONObject) oid).get(VENDOR).toString());
            });

            FileWriter fileWriter = new FileWriter("/home/nikunj/Downloads/Task/aa-snmp-devices.json");

            fileWriter.write(array.toJSONString());

            fileWriter.flush();

            System.out.println("Count: " + count);

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private static void getNewOID()
    {
        int count = 0;
        try (FileInputStream file = new FileInputStream(new File("/home/nikunj/Downloads/Task/Copy_Supported_SNMP_devices_April_2022.xlsx")))
        {
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet)
            {
                var oid = "."+row.getCell(0).toString();

                var model = row.getCell(2).toString();

                var vendor = row.getCell(1).toString();

                var capabilites = row.getCell(3).toString().split(",");

                var type = row.getCell(3).toString();

                if (vendorType.containsKey(vendor))
                {
                    // modify vendor
                    vendor = vendorType.get(vendor);
                }

                if (capabilites.length > 0)
                {
                    type = capabilites[0];
                }

                if (catalogType.containsKey(type))
                {
                    type = catalogType.get(type);
                    if (snmpDevices.containsKey(oid))
                    {
                        // oid is there
                        var typeMap = snmpDevices.get(oid);

                        if (typeMap.containsKey(type) && typeMap.get(type).contains(model))
                        {
                            // type is there but model is not there
                            typeMap.get(type).add(model);

                            var jsonObject = new JSONObject();

                            jsonObject.put(OID, oid);
                            jsonObject.put(MODEL, model);
                            jsonObject.put(VENDOR, vendor);
                            jsonObject.put(TYPE, type);

                            array.add(jsonObject);

                            count++;
                        }
                    }
                    else
                    {
                        // oid is not there
                        Map<String, Set<String>> typeMap = new HashMap<>();

                        Set<String> modelSet = new HashSet<>();

                        modelSet.add(model);

                        typeMap.put(type, modelSet);

                        snmpDevices.put(oid, typeMap);

                        var jsonObject = new JSONObject();

                        jsonObject.put(OID, oid);
                        jsonObject.put(MODEL, model);
                        jsonObject.put(VENDOR, vendor);
                        jsonObject.put(TYPE, type);

                        array.add(jsonObject);

                        count++;
                    }
                }
            }

            System.out.println("New record: "+count+" | final size: "+ array.size());

            FileWriter fileWriter = new FileWriter("/home/nikunj/Downloads/Task/updated-snmp-devices.json");

//            for (String oid : snmpDevices.keySet())
//            {
//                var typeMap = snmpDevices.get(oid);
//                for (String type : typeMap.keySet())
//                {
//                    var modelSet = typeMap.get(type);
//
//                    Iterator<String> modelItr = modelSet.iterator();
//
//                    while (modelItr.hasNext())
//                    {
//                        var jsonObject = new JSONObject();
//
//                        jsonObject.put(OID, oid);
//                        jsonObject.put(TYPE, type);
//                        jsonObject.put(MODEL, modelItr.next());
//                        jsonObject.put(VENDOR, )
//                    }
//                }
//            }

//            jsonArray.add(snmpDevices.values());

//            for (String str : snmpDevices)
//            {
//                JSONObject device = new JSONObject();
//
//                var properties = str.split(SPLIT);
//
////                System.out.println(Arrays.toString(properties));
//
//                device.put(OID, properties[0]);
//                device.put(TYPE, properties[1]);
//                device.put(MODEL, properties[2]);
//                device.put(VENDOR, properties[3]);
//
//                //System.out.println(device);
//
//                jsonArray.add(device);
//            }

//            for (JSONObject device : snmpDevices.values())
//            {
//                jsonArray.add(device);
//            }

//            for (String str : newSnmpDevices)
//            {
//                JSONObject device = new JSONObject();
//
//                var properties = str.split(SPLIT);
//
//                device.put(OID, properties[0]);
//                device.put(TYPE, properties[1]);
//                device.put(MODEL, properties[2]);
//                device.put(VENDOR, properties[3]);
//
//                newJsonArray.add(device);
//            }

//            for (JSONObject device : newSnmpDevices.values())
//            {
//                newJsonArray.add(device);
//            }

            fileWriter.write(array.toJSONString());

            fileWriter.flush();

//            FileWriter fw = new FileWriter("/home/nikunj/Downloads/Task/new-snmp-devices.json");
//
//            fw.write(newJsonArray.toJSONString());
//
//            fw.flush();
//            System.out.println(snmpDevices.size());

//            vendors.stream().forEach(item -> {
//                System.out.println(item);
//            });

//            xlsVendors.stream().forEach(item -> System.out.println(item));
//
//            System.out.println("vendorsFromJSON: " + vendorsFromJSON.size());

//            System.out.println(vendors);

//            System.out.println(jsonArray);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

//    private static void insert(Row row)
//    {
//        var oid = "."+row.getCell(0).toString();
//
//        var model = row.getCell(2).toString();
//
//        var vendor = row.getCell(1).toString();
//
//        if (vendorType.containsKey(vendor))
//        {
//            System.out.println("Vendor value: "+vendor);
//            vendor = vendorType.get(vendor);
//        }
//
//        var capabilites = row.getCell(3).toString().split(",");
//
//        String type = "null";
//
//        if (capabilites.length == 0)
//        {
//            type = catalogType.get(row.getCell(3).toString());
//
//            var device = oid + SPLIT +
//                    type + SPLIT +
//                    model + SPLIT +
//                    vendor;
//
//            if (!snmpDevices.contains(device))
//            {
//                snmpDevices.add(device);
//
//                newSnmpDevices.add(device);
//                counter++;
//
//                return;
//            }
//        }
//        else
//        {
//            if (capabilites[0].equals("Router"))
//            {
//                type = "Router";
//
//                var device = oid + SPLIT +
//                        type + SPLIT +
//                        model + SPLIT +
//                        vendor;
//
//                if (!snmpDevices.contains(device))
//                {
//                    snmpDevices.add(device);
//
//                    newSnmpDevices.add(device);
//
//
//                    counter++;
//
//                    return;
//                }
//            }
//        }
////        var device = value.get(OID).toString() + "," +
////                value.get(TYPE).toString()+ ","  +
////                value.get(MODEL).toString() + "," +
////                value.get(VENDOR).toString();
//
////        var device = oid + SPLIT +
////                type + SPLIT +
////                model + SPLIT +
////                vendor;
////
////        if (!snmpDevices.contains(device))
////        {
////            snmpDevices.add(device);
////
////            newSnmpDevices.add(device);
////
////            System.out.println(device);
////
////            counter++;
////
////            return;
////        }
//
//
////        System.out.println(device+" already exits");
//    }
}
