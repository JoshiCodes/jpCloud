package de.joshizockt.cloud.api;

import com.sun.istack.internal.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class CloudConfig {

    public static final String CONFIGURATION_FOLDER = "config";
    public static final String MESSAGE_NOT_FOUND = "404: Message not found.";
    public static final String NOT_STRING = "Error: Object is not a String.";

    private URL res;
    private File file;
    private File ext;
    private File folder;

    private JSONObject object;

    public CloudConfig(String config) { this(config, CONFIGURATION_FOLDER); }

    public CloudConfig(String config, String folder) {
        this(config, folder, false);
    }

    public CloudConfig(String config, String folder, boolean copyDefaults) {
        ClassLoader classLoader = getClass().getClassLoader();
        this.folder = new File(folder);
        this.folder.mkdirs();
        this.res = classLoader.getResource(folder + "/" + config);
        this.file = new File(res.getFile());
        this.ext = new File(folder + "/" + config);

        if(copyDefaults) {
            copyDefaults();
        }

        this.object = readJson();

    }

    public boolean copyDefaults() {
        if(!ext.exists()) {
            try (InputStream in = res.openStream()) {
                Files.copy(in, ext.toPath());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Shows if config File exists.
     * @return boolean
     */
    public boolean fileExists() {
        if(ext == null) return false;
        return ext.exists();
    }

    /**
     * Show if Param 'key' Exists.
     * @param key The Key of the Config
     * @return boolean
     */
    public boolean valueExists(String key) {
        return object.containsKey(key);
    }

    /**
     * Return the Object of the JsonObject. Can be null
     * @param key The Key of the Config
     * @return Object
     * @see this.valueExists(String);
     */
    @Nullable
    public Object get(String key) {
        if(object.containsKey(key)) {
            return object.get(key);
        }
        return null;
    }

    /**
     * Return needed String. If not found, MESSAGE_NOT_FOUND Message will returned.
     * @param key The Key of the Config
     * @return String
     * @see this.get(String);
     */
    @Nullable
    public String getString(String key) {
        Object ob = get(key);
        if(ob != null) {
            if(ob instanceof String) {
                return (String)ob;
            }
            return NOT_STRING;
        }
        return null;
    }

    /**
     * Returns needed Boolean. If not found, false will returned.
     * @param key The Key of the Config
     * @return boolean
     * @see this.get(String);
     */
    public Boolean getBoolean(String key) {
        Object ob = get(key);
        if(ob != null) {
            if(ob instanceof Boolean) {
                return (Boolean) ob;
            }
            return false;
        }
        return false;
    }

    /**
     * Returns needed Integer. If not found, return 0
     * @param key The Key of the Config
     * @return Integer
     * @see this.get(String);
     */
    public Integer getInteger(String key) {
        Object ob = get(key);
        if(ob != null) {
            if(ob instanceof Integer) {
                return (int) ob;
            } else if(ob instanceof Long) {
                return Integer.valueOf(String.valueOf((long)ob));
            }
        }
        return 0;
    }

    /**
     * Returns needed Long. If not found, return 0
     * @param key The Key of the Config
     * @return Long
     * @see this.get(String);
     */
    public Long getLong(String key) {
        Object ob = get(key);
        if(ob != null) {
            if(ob instanceof Long) {
                return (Long) ob;
            }
        }
        return (long)0;
    }

    /**
     * Return needed Float. If not, null will returned.
     * @param key The Key of the Config
     * @return Float
     * @see this.get(String);
     */
    @Deprecated
    @Nullable
    public Float getFloat(String key) {
        Object ob = get(key);
        if(ob != null) {
            if(ob instanceof Float) {
                return (Float) ob;
            }
            return null;
        }
        return null;
    }

    /**
     * Returns needed Double. If not found, return 0.0
     * @param key The Key of the Config
     * @return Double
     * @see this.get(String);
     */
    public Double getDouble(String key) {
        Object ob = get(key);
        if(ob != null) {
            if(ob instanceof Double) {
                return (Double) ob;
            }
            return 0.0;
        }
        return 0.0;
    }

    /**
     * Returns needed JSONArray. If not found, returns null.
     * @param key The Key of the Config
     * @return JSONArray
     * @see this.get(String);
     */
    public JSONArray getArray(String key) {
        Object ob = get(key);
        if(ob != null) {
            if(ob instanceof JSONArray) {
                return (JSONArray)ob;
            }
        }
        return new JSONArray();
    }

    public boolean set(String key, Object value) {

        JSONObject json = readJson();
        json.put(key, value);

        try (FileWriter writer = new FileWriter(ext)) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
            scriptEngine.put("jsonString", json.toJSONString());
            scriptEngine.eval("result = JSON.stringify(JSON.parse(jsonString), null, 2)");
            writer.write((String)scriptEngine.get("result"));
            //writer.write(json.toJSONString());
            writer.flush();
            return true;
        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns complete JSONObject of the File.
     * @deprecated
     * @return JSONObject.
     */
    @Deprecated
    @Nullable
    public JSONObject readJson() {
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(ext));

            JSONObject jsonObject = (JSONObject) obj;

            return jsonObject;

        } catch (Exception e) {  }
        return new JSONObject();
    }

}
