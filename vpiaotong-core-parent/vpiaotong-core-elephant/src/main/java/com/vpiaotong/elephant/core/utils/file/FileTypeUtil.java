package com.vpiaotong.elephant.core.utils.file;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 判断文件类型
 * 
 * @author Acmen
 */
public class FileTypeUtil {

    /**
     * 私有化构造函数，防止没构建
     */
    private FileTypeUtil() {
        throw new IllegalAccessError();
    }

    /** 文件类型Map */
    private final static Map<FileTypeEnum, String> FILE_TYPE_MAP = new LinkedHashMap<FileTypeEnum, String>();

    static {
        // 初始化文件类型信息
        getAllFileType();
    }

    /**
     * 常见文件头信息
     */
    private static void getAllFileType() {
        FILE_TYPE_MAP.put(FileTypeEnum.JPEG, "FFD8FF");
        FILE_TYPE_MAP.put(FileTypeEnum.PNG, "89504E47");
        FILE_TYPE_MAP.put(FileTypeEnum.GIF, "47494638");
        FILE_TYPE_MAP.put(FileTypeEnum.BMP, "424D");
        FILE_TYPE_MAP.put(FileTypeEnum.ICO, "000001000");
        FILE_TYPE_MAP.put(FileTypeEnum.TIFF, "49492A00");
        FILE_TYPE_MAP.put(FileTypeEnum.DWG, "41433130");
        FILE_TYPE_MAP.put(FileTypeEnum.JS, "696B2E71623D696B2E71");
        FILE_TYPE_MAP.put(FileTypeEnum.CSS, "48544D4C207B0D0A0942");
        FILE_TYPE_MAP.put(FileTypeEnum.JSP, "3C2540207061676520");
        FILE_TYPE_MAP.put(FileTypeEnum.HTML, "68746D6C3E");
        FILE_TYPE_MAP.put(FileTypeEnum.RTF, "7B5C727466");
        FILE_TYPE_MAP.put(FileTypeEnum.XML, "3C3F786D6C");
        FILE_TYPE_MAP.put(FileTypeEnum.PSD, "38425053");
        FILE_TYPE_MAP.put(FileTypeEnum.EML, "44656C69766572792D646174653A");
        FILE_TYPE_MAP.put(FileTypeEnum.DBX, "CFAD12FEC5FD746F");
        FILE_TYPE_MAP.put(FileTypeEnum.PST, "2142444E");
        FILE_TYPE_MAP.put(FileTypeEnum.XLS_DOC, "D0CF11E0");
        FILE_TYPE_MAP.put(FileTypeEnum.XLSX_DOCX, "504B030414000600080000002100");
        FILE_TYPE_MAP.put(FileTypeEnum.MDB, "5374616E64617264204A");
        FILE_TYPE_MAP.put(FileTypeEnum.WPD, "FF575043");
        FILE_TYPE_MAP.put(FileTypeEnum.EPS_PS, "252150532D41646F6265");
        FILE_TYPE_MAP.put(FileTypeEnum.PDF, "255044462D312E");
        FILE_TYPE_MAP.put(FileTypeEnum.QDF, "AC9EBD8F");
        FILE_TYPE_MAP.put(FileTypeEnum.PWL, "E3828596");
        FILE_TYPE_MAP.put(FileTypeEnum.WAV, "57415645");
        FILE_TYPE_MAP.put(FileTypeEnum.AVI, "41564920");
        FILE_TYPE_MAP.put(FileTypeEnum.RAM, "2E7261FD");
        FILE_TYPE_MAP.put(FileTypeEnum.RM, "2E524D46");
        FILE_TYPE_MAP.put(FileTypeEnum.MPG, "000001BA");
        FILE_TYPE_MAP.put(FileTypeEnum.MOV, "6D6F6F76");
        FILE_TYPE_MAP.put(FileTypeEnum.ASF, "3026B2758E66CF11");
        FILE_TYPE_MAP.put(FileTypeEnum.MP4, "00000020667479706d70");
        FILE_TYPE_MAP.put(FileTypeEnum.MP3, "49443303000000002176");
        FILE_TYPE_MAP.put(FileTypeEnum.FLV, "464C5601050000000900");
        FILE_TYPE_MAP.put(FileTypeEnum.MID, "4D546864");
        FILE_TYPE_MAP.put(FileTypeEnum.EXE, "4D5A9000030000000400");
        FILE_TYPE_MAP.put(FileTypeEnum.JAVA, "7061636B61676520");
        FILE_TYPE_MAP.put(FileTypeEnum.CLASS, "CAFEBABE0000002E00");
        FILE_TYPE_MAP.put(FileTypeEnum.JAR, "504B03040A000000");
        FILE_TYPE_MAP.put(FileTypeEnum.ZIP, "504B0304");
        FILE_TYPE_MAP.put(FileTypeEnum.RAR, "52617221");
        FILE_TYPE_MAP.put(FileTypeEnum.CHM, "49545346030000006000");
    }

    /**
     * 文件类型枚举类
     * 
     * @author zhaoTh
     */
    public enum FileTypeEnum {
        /** JEPG */
        JPEG,
        /** PNG */
        PNG,
        /** GIF */
        GIF,
        /** ICO */
        ICO,
        /** TIFF */
        TIFF,
        /** Windows Bitmap */
        BMP,
        /** CAD */
        DWG,
        /** js */
        JS,
        /** css */
        CSS,
        /** JSP Archive */
        JSP,
        /** HTML */
        HTML,
        /** Rich Text Format */
        RTF,
        /** XML */
        XML,
        /** Adobe Photoshop */
        PSD,
        /** Email [thorough only] */
        EML,
        /** Outlook Express */
        DBX,
        /** Outlook (pst) */
        PST,
        /** MS Word/Excel */
        XLS_DOC,
        /** MS Word/Excel */
        XLSX_DOCX,
        /** MS Access */
        MDB,
        /** WordPerfect */
        WPD,
        /** Postscript */
        EPS_PS,
        /** Adobe Acrobat */
        PDF,
        /** Quicken */
        QDF,
        /** Windows Password */
        PWL,
        /** Wave */
        WAV,
        /** AVI */
        AVI,
        /** Real Audio */
        RAM,
        /** Real Media */
        RM,
        /** MPEG (mpg) */
        MPG,
        /** Quicktime */
        MOV,
        /** Windows Media */
        ASF,
        /** MP4 */
        MP4,
        /** MP3 */
        MP3,
        /** FLV */
        FLV,
        /** MIDI */
        MID,
        /** EXE Archive */
        EXE,
        /** JAVA Archive */
        JAVA,
        /** CLASS Archive */
        CLASS,
        /** JAR Archive */
        JAR,
        /** ZIP Archive */
        ZIP,
        /** RAR Archive */
        RAR,
        /** CHM Archive */
        CHM;
    }

    /**
     * 根据文件流取得文件类型
     * 
     * @param fileByte
     *            文件字节流数据
     * @return 文件类型枚举类
     */
    public final static FileTypeEnum getFileTypeByStream(byte[] fileByte) {
        String filetypeHex = getFileTypeHexString(fileByte);
        if (null != filetypeHex) {
            for (Map.Entry<FileTypeEnum, String> entry : FILE_TYPE_MAP.entrySet()) {
                String fileTypeHexValue = entry.getValue();
                if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    /**
     * 将文件头转换成16进制字符串
     * 
     * @param fileByte
     *            文件字节流数据
     * @return fileTypeHex
     */
    private final static String getFileTypeHexString(byte[] fileByte) {
        StringBuilder stringBuilder = new StringBuilder();
        if (fileByte == null || fileByte.length <= 0) {
            return null;
        }
        for (int i = 0; i < fileByte.length; i++) {
            int v = fileByte[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
