/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elecciones2023datos;

/**
 *
 * @author Fede
 */
import java.io.File;
import java.io.IOException;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import java.util.prefs.Preferences;

public class IniFileReaderWriter {

    private File file;
    private Ini ini;

    public IniFileReaderWriter(String filePath) throws IOException {
        this.file = new File(filePath);
        this.ini = new Ini();
        this.ini.load(this.file);
    }

    public String getValue(String section, String key) {
        Preferences prefs = new IniPreferences(this.ini);
        return prefs.node(section).get(key, null);
    }

    public void setValue(String section, String key, String value) throws IOException {
        Preferences prefs = new IniPreferences(this.ini);
        prefs.node(section).put(key, value);
        this.ini.store(this.file);
    }

}