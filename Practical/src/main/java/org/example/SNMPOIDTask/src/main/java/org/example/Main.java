package org.example;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main
{
    private static final Map<String, String> catalogType = new HashMap<>();
    private static final Map<String, JSONObject> snmpDevices = new HashMap<String, JSONObject>();

    private static final Map<String, Map<String, JSONObject>> snmpDevicesV2 = new HashMap<>();
    private static final Map<String, String> vendorType = new HashMap<>();
    private static final Set<String> xlsVendors = new HashSet<>();
    private static final Map<String, JSONObject> newSnmpDevices = new HashMap<>();


    private static final Set<String> vendorsFromJSON = new HashSet<>();

    public static String TYPE = "snmp.device.catalog.type";
    public static String MODEL = "snmp.device.catalog.model";
    public static String VENDOR = "snmp.device.catalog.vendor";
    public static String OID = "snmp.device.catalog.oid";

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
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("/home/nikunj/Downloads/Task/snmp-devices.json"))
        {
            Object obj = parser.parse(reader);

            JSONArray oidArray = (JSONArray) obj;

            oidArray.forEach(oid ->
            {

                JSONObject value = ((JSONObject) oid);

                if (value.get(VENDOR).toString().equals("TELDAT, S.A."))
                {
                    value.put(VENDOR, vendorType.get("Teldat"));
                }

                if (snmpDevices.containsKey(((JSONObject) oid).get(OID).toString()))
                {
                    System.out.println("DUPLICATE: " + ((JSONObject) oid).get(OID));
                }
//                snmpDevices.computeIfAbsent(((JSONObject) oid).get(OID).toString(), key -> {
//                    return snmpDevices.put(((JSONObject) oid).get(OID).toString(), ((JSONObject) obj));
//                });




                if (snmpDevicesV2.containsKey(((JSONObject) oid).get(OID).toString()))
                {
                    // now check type
                    if (!snmpDevicesV2.get(((JSONObject) oid).get(OID).toString())
                            .containsKey(((JSONObject) oid).get(TYPE).toString()))
                    {
                        // check does not match
                        // insert new data
                        var innerMap = snmpDevicesV2.get(((JSONObject) oid).get(OID).toString());

                        innerMap.put(((JSONObject) oid).get(TYPE).toString(), value);
                    }
                }
                else
                {
                    HashMap<String, JSONObject> innerMap = new HashMap<>();

                    innerMap.put(((JSONObject) oid).get(TYPE).toString(), value);
                    snmpDevicesV2.put(((JSONObject) oid).get(OID).toString(), innerMap);
                }




                snmpDevices.putIfAbsent(((JSONObject) oid).get(OID).toString(),
                        value);

                // adding all unique vendors to vendorsFromJSON
                vendorsFromJSON.add(((JSONObject) oid).get(VENDOR).toString());
            });

            System.out.println("Size: " + snmpDevices.size());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private static void insert(Row row)
    {
        var newDevice = new JSONObject();

        newDevice.put(TYPE, row.getCell(3).toString());

        newDevice.put(MODEL, row.getCell(2).toString());

        if (!vendorsFromJSON.contains(row.getCell(1).toString()))
        {
            // this will print all the vendors which are not in the xmls file
            System.out.println(row.getCell(1).toString());
        }

        if (vendorType.containsKey(row.getCell(1).toString()))
        {
            newDevice.put(VENDOR, vendorType.get(row.getCell(1).toString()));
        } else
        {
            newDevice.put(VENDOR, row.getCell(1).toString());
        }

        newDevice.put(OID, "." + row.getCell(0).toString());

//        System.out.println(row.getCell(1).toString()+" - "+vendors.contains(row.getCell(1).toString()));

//        System.out.print("old: "+snmpDevices.size());

        snmpDevices.put("." + row.getCell(0), newDevice);

        newSnmpDevices.put("."+row.getCell(0).toString(), newDevice);


//        System.out.println("  new: "+snmpDevices.size());
    }

    private static void getNewOID()
    {
        try (FileInputStream file = new FileInputStream(new File("/home/nikunj/Downloads/Task/Copy_Supported_SNMP_devices_April_2022.xlsx")))
        {
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            int counter = 0;

            for (Row row : sheet)
            {

                if (snmpDevicesV2.containsKey("."+ row.getCell(0).toString()))
                {
                    // oid is there
                    // check for type
                    // before split it

                    var splitCapabilites = row.getCell(3).toString().split(",");

                    if (splitCapabilites.length == 0)
                    {
                        // now check for type
                        if (!snmpDevicesV2.get("."+row.getCell(0).toString())
                                .containsKey(row.getCell(3).toString()))
                        {
                            // type does not exist
                            if (catalogType.containsKey(row.getCell(3).toString()))
                            {
                                insert(row);
                                counter++;
                            }
                        }
                    }
                    else
                    {
                        if (splitCapabilites[0].equals("Router"))
                        {
                            insert(row);
                            counter++;
                        }
                    }
                }
                else
                {
                    var splitCapabilites = row.getCell(3).toString().split(",");

                    if (splitCapabilites.length == 0)
                    {
                        // now check for type
                        if (!snmpDevicesV2.get("."+row.getCell(0).toString())
                                .containsKey(row.getCell(3).toString()))
                        {
                            // type does not exist
                            if (catalogType.containsKey(row.getCell(3).toString()))
                            {
                                insert(row);
                                counter++;
                            }
                        }
                    }
                    else
                    {
                        if (splitCapabilites[0].equals("Router"))
                        {
                            insert(row);
                            counter++;
                        }
                    }
                }




                if (!snmpDevices.containsKey("." + row.getCell(0)))
                {
                    var capabilities = row.getCell(3).toString().split(",");
                    if (capabilities.length == 0)
                    {
                        if (catalogType.containsKey(row.getCell(3)))
                        {

                            insert(row);
                            counter++;
                        }
                    } else
                    {
                        if (capabilities[0].equals("Router"))
                        {
                            insert(row);
                            counter++;
                        }
                    }
                }
            }

            FileWriter fileWriter = new FileWriter("/home/nikunj/Downloads/Task/snmp-devices.json");

            var jsonArray = new JSONArray();

//            jsonArray.add(snmpDevices.values());

            for (JSONObject device : snmpDevices.values())
            {
                jsonArray.add(device);
            }

            var newJsonArray = new JSONArray();

            for (JSONObject device : newSnmpDevices.values())
            {
                newJsonArray.add(device);
            }

            fileWriter.write(jsonArray.toJSONString());

            fileWriter.flush();

            FileWriter fw = new FileWriter("/home/nikunj/Downloads/Task/new-snmp-devices.json");

            fw.write(newJsonArray.toJSONString());

            fw.flush();

            System.out.println(snmpDevices.size());

//            vendors.stream().forEach(item -> {
//                System.out.println(item);
//            });

            xlsVendors.stream().forEach(item -> System.out.println(item));

            System.out.println("vendorsFromJSON: " + vendorsFromJSON.size());

//            System.out.println(vendors);

//            System.out.println(jsonArray);
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}