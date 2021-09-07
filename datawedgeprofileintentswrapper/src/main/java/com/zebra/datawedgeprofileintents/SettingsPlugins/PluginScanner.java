package com.zebra.datawedgeprofileintents.SettingsPlugins;

import android.os.Bundle;

import com.zebra.datawedgeprofileenums.SC_E_1D_MARGINLESS_DECODE_EFFORT_LEVEL;
import com.zebra.datawedgeprofileenums.SC_E_AIM_MODE;
import com.zebra.datawedgeprofileenums.SC_E_AIM_TYPE;
import com.zebra.datawedgeprofileenums.SC_E_CHARSET_NAME;
import com.zebra.datawedgeprofileenums.SC_E_CODE11_VERIFY_CHECK_DIGIT;
import com.zebra.datawedgeprofileenums.SC_E_CODE_ID_TYPE;
import com.zebra.datawedgeprofileenums.SC_E_CONCAT_MODE;
import com.zebra.datawedgeprofileenums.SC_E_I2OF5_CHECK_DIGIT;
import com.zebra.datawedgeprofileenums.SC_E_ILLUMINATION_MODE;
import com.zebra.datawedgeprofileenums.SC_E_INVERSE;
import com.zebra.datawedgeprofileenums.SC_E_INVERSE1DMODE_MODE;
import com.zebra.datawedgeprofileenums.SC_E_LCD_MODE;
import com.zebra.datawedgeprofileenums.SC_E_LINEAR_SECURITY_LEVEL;
import com.zebra.datawedgeprofileenums.SC_E_LINK_MODE;
import com.zebra.datawedgeprofileenums.SC_E_MSI_CHECK_DIGIT;
import com.zebra.datawedgeprofileenums.SC_E_MSI_CHECK_DIGIT_SCHEME;
import com.zebra.datawedgeprofileenums.SC_E_PICKLIST_MODE;
import com.zebra.datawedgeprofileenums.SC_E_POOR_QUALITY_DECODE_LEVEL;
import com.zebra.datawedgeprofileenums.SC_E_PREAMBLE;
import com.zebra.datawedgeprofileenums.SC_E_SCANNER_IDENTIFIER;
import com.zebra.datawedgeprofileenums.SC_E_SCANNINGMODE;
import com.zebra.datawedgeprofileenums.SC_E_SECURITY_LEVEL;
import com.zebra.datawedgeprofileenums.SC_E_UPCEAN_BOOKLAND_FORMAT;
import com.zebra.datawedgeprofileenums.SC_E_UPCEAN_COUPON_REPORT;
import com.zebra.datawedgeprofileenums.SC_E_UPCEAN_SECURITY_LEVEL;
import com.zebra.datawedgeprofileenums.SC_E_UPCEAN_SUPPLEMENTAL_MODE;
import com.zebra.datawedgeprofileenums.SC_E_VOLUME_SLIDER_TYPE;
import com.zebra.datawedgeprofileintents.DWProfileSetConfigSettings;

import androidx.annotation.RequiresPermission;

/////////////////////////////////////////////////////////////////////////////////////////
// BARCODE Plugin... THE BIG ONE !!!!
/////////////////////////////////////////////////////////////////////////////////////////
public class PluginScanner
{
    /*
    Enable or disable current scanner input module.
    Useful to control when scan should be available in an application
    Default is true
     */
    public Boolean scanner_input_enabled = true;

    /*
    Use it to force the scanner selection
    You should provide an index from 0-n based on the index from ENUMERATE_SCANNERS API
    http://techdocs.zebra.com/datawedge/6-7/guide/api/enumeratescanners/
     */
    //public String scanner_selection = "auto";

    /*
     Set the scanner you want to use for this profile
     Default is internal imager
    */
    public SC_E_SCANNER_IDENTIFIER scanner_selection_by_identifier = SC_E_SCANNER_IDENTIFIER.AUTO;


    public class Decoders {
        /****************************************/
        /*              Decoders                */
        /****************************************/
        public Boolean decoder_australian_postal = null;
        public Boolean decoder_aztec = null;
        public Boolean decoder_canadian_postal = null;
        public Boolean decoder_chinese_2of5 = null;
        public Boolean decoder_codabar = null;
        public Boolean decoder_code11 = null;
        public Boolean decoder_code128 = null;
        public Boolean decoder_code39 = null;
        public Boolean decoder_code93 = null;
        public Boolean decoder_composite_ab = null;
        public Boolean decoder_composite_c = null;
        public Boolean decoder_d2of5 = null;
        public Boolean decoder_datamatrix = null;
        public Boolean decoder_dutch_postal = null;
        public Boolean decoder_ean13 = null;
        public Boolean decoder_ean8 = null;
        public Boolean decoder_gs1_databar = null;
        public Boolean decoder_hanxin = null;
        public Boolean decoder_i2of5 = null;
        public Boolean decoder_japanese_postal = null;
        public Boolean decoder_korean_3of5 = null;
        public Boolean decoder_mailmark = null;
        public Boolean decoder_matrix_2of5 = null;
        public Boolean decoder_maxicode = null;
        public Boolean decoder_micropdf = null;
        public Boolean decoder_microqr = null;
        public Boolean decoder_msi = null;
        public Boolean decoder_pdf417 = null;
        public Boolean decoder_qrcode = null;
        public Boolean decoder_signature = null;
        public Boolean decoder_tlc39 = null;
        public Boolean decoder_trioptic39 = null;
        public Boolean decoder_uk_postal = null;
        public Boolean decoder_upca = null;
        public Boolean decoder_upce0 = null;
        public Boolean decoder_upce1 = null;
        public Boolean decoder_us4state = null;
        public Boolean decoder_usplanet = null;
        public Boolean decoder_uspostnet = null;
        public Boolean decoder_webcode = null;
    }
    public Decoders Decoders = new Decoders();

    public class DecodersParams {
        /****************************************/
        /*              Decoders                */
        /****************************************/
        public Boolean decoder_codabar_clsi_editing = null;
        public Integer decoder_codabar_length1 = null;
        public Integer decoder_codabar_length2 = null;
        public Boolean decoder_codabar_notis_editing = null;
        public Boolean decoder_codabar_redundancy = null;

        public Integer decoder_code11_length1 = null;
        public Integer decoder_code11_length2 = null;
        public Boolean decoder_code11_redundancy = null;
        public Boolean decoder_code11_report_check_digit = null;
        public SC_E_CODE11_VERIFY_CHECK_DIGIT decoder_code11_verify_check_digit = null;

        public Boolean decoder_code128_check_isbt_table = null;
        public Boolean decoder_code128_enable_ean128 = null;
        public Boolean decoder_code128_enable_isbt128 = null;
        public Boolean decoder_code128_enable_plain = null;
        public SC_E_CONCAT_MODE decoder_code128_isbt128_concat_mode = null;
        public Integer decoder_code128_length1 = null;
        public Integer decoder_code128_length2 = null;
        public Boolean decoder_code128_redundancy = null;
        public SC_E_SECURITY_LEVEL decoder_code128_security_level = null;
        public Boolean code128_ignore_fnc4 = null;

        public Boolean decoder_code39_convert_to_code32 = null;
        public Boolean decoder_code39_full_ascii = null;
        public Integer decoder_code39_length1 = null;
        public Integer decoder_code39_length2 = null;
        public Boolean decoder_code39_redundancy = null;
        public Boolean decoder_code39_report_check_digit = null;
        public Boolean decoder_code39_report_code32_prefix = null;
        public SC_E_SECURITY_LEVEL decoder_code39_security_level = null;
        public Boolean decoder_code39_verify_check_digit = null;

        public Integer decoder_code93_length1 = null;
        public Integer decoder_code93_length2 = null;
        public Boolean decoder_code93_redundancy = null;

        public SC_E_LINK_MODE decoder_composite_ab_ucc_link_mode = null;

        public Integer decoder_d2of5_length1 = null;
        public Integer decoder_d2of5_length2 = null;
        public Boolean decoder_d2of5_redundancy = null;

        public Boolean decoder_ean8_convert_to_ean13 = null;

        public Boolean decoder_gs1_databar_exp = null;
        public Boolean decoder_gs1_databar_lim = null;
        public SC_E_SECURITY_LEVEL decoder_gs1_lim_security_level = null;

        public SC_E_INVERSE decoder_hanxin_inverse = null;

        public SC_E_I2OF5_CHECK_DIGIT decoder_i2of5_check_digit = null;
        public Integer decoder_i2of5_length1 = null;
        public Integer decoder_i2of5_length2 = null;
        public Boolean decoder_i2of5_redundancy = null;
        public Boolean decoder_i2of5_report_check_digit = null;
        public SC_E_SECURITY_LEVEL decoder_i2of5_security_level = null;
        public Boolean decoder_i2of5_convert_to_ean13 = null;
        public Boolean decoder_itf14_convert_to_ean13 = null;

        public Integer decoder_matrix_2of5_length1 = null;
        public Integer decoder_matrix_2of5_length2 = null;
        public Boolean decoder_matrix_2of5_redundancy = null;
        public Boolean decoder_matrix_2of5_report_check_digit = null;
        public Boolean decoder_matrix_2of5_verify_check_digit = null;

        public SC_E_MSI_CHECK_DIGIT decoder_msi_check_digit = null;
        public SC_E_MSI_CHECK_DIGIT_SCHEME decoder_msi_check_digit_scheme = null;
        public Integer decoder_msi_length1 = null;
        public Integer decoder_msi_length2 = null;
        public Boolean decoder_msi_redundancy = null;
        public Boolean decoder_msi_report_check_digit = null;

        public Boolean decoder_trioptic39_redundancy = null;

        public Boolean decoder_uk_postal_report_check_digit = null;

        public SC_E_PREAMBLE decoder_upca_preamble = null;
        public Boolean decoder_upca_report_check_digit = null;

        public Boolean decoder_upce0_convert_to_upca = null;
        public SC_E_PREAMBLE decoder_upce0_preamble = null;
        public Boolean decoder_upce0_report_check_digit = null;

        public Boolean decoder_upce1_convert_to_upca = null;
        public SC_E_PREAMBLE decoder_upce1_preamble = null;
        public Boolean decoder_upce1_report_check_digit = null;

        public Boolean decoder_us4state_fics = null;

        public Boolean decoder_usplanet_report_check_digit = null;
    }
    public DecodersParams DecodersParams = new DecodersParams();

    public class UpcEan
    {
        /****************************************/
        /*              UPCEAN Specific         */
        /****************************************/
        public Boolean databar_to_upc_ean = null;
        public Boolean upcean_bookland = null;
        public SC_E_UPCEAN_BOOKLAND_FORMAT upcean_bookland_format = null;
        public Boolean upcean_coupon = null;
        public SC_E_UPCEAN_COUPON_REPORT upcean_coupon_report = null;
        public Boolean upcean_ean_zero_extend = null;
        public Integer upcean_retry_count = null;
        public SC_E_UPCEAN_SECURITY_LEVEL upcean_security_level = null;
        public Boolean upcean_supplemental2 = null;
        public Boolean upcean_supplemental5 = null;
        public SC_E_UPCEAN_SUPPLEMENTAL_MODE upcean_supplemental_mode = null;
        public Boolean upcean_linear_decode = null;
        public Boolean upcean_random_weight_check_digit = null;
    }
    public UpcEan UpcEan = new UpcEan();

    public class ReaderParams
    {
        /****************************************/
        /*              Reader parameter        */
        /****************************************/
        public SC_E_AIM_MODE aim_mode = null;
        public SC_E_CHARSET_NAME charset_name = null;
        public SC_E_ILLUMINATION_MODE illumination_mode = null;
        public SC_E_INVERSE1DMODE_MODE inverse_1d_mode = null;
        public SC_E_LCD_MODE lcd_mode = null;
        public SC_E_LINEAR_SECURITY_LEVEL linear_security_level = null;
        public Integer low_power_timeout = null; //0-1000
        public SC_E_PICKLIST_MODE picklist = null;
        public SC_E_POOR_QUALITY_DECODE_LEVEL poor_quality_bcdecode_effort_level = null;
        public Integer aim_timer = null; //0-60000
        public SC_E_AIM_TYPE aim_type = null;
        public Integer beam_timer = null; //0-60000
        public Integer different_barcode_timeout = null; //0-5000 must be a multiple value of 500 (0 included)
        public Integer same_barcode_timeout = null; //0-5000 must be a multiple value of 500 (0 included)
        public SC_E_SCANNINGMODE scanning_mode = null;
        public SC_E_1D_MARGINLESS_DECODE_EFFORT_LEVEL oneD_marginless_decode_effort_level = null;

    }
    public ReaderParams ReaderParams = new ReaderParams();

    public class ScanParams
    {
        public SC_E_CODE_ID_TYPE code_id_type = null;
        public Boolean decode_haptic_feedback = null;
        public String decode_audio_feedback_uri = null;
        public Boolean decoding_led_feedback = null;
        public Integer good_decode_led_timer = null; //0-1000
        public SC_E_VOLUME_SLIDER_TYPE volume_slider_type = null;
    }
    public ScanParams ScanParams = new ScanParams();

    public class MultiBarcode
    {
        /*
         Number of multibarcode to read
         Default is 5
        */
        public Integer multi_barcode_count = null;
    }
    public MultiBarcode MultiBarcode = new MultiBarcode();

    /*
    Undocumented and not available from DataWedge Configuration GUI
    Comes from GetConfig Bundle analysis
     */
    public class MarginLess
    {
        /****************************************/
        /*     Marginless Decode parameters     */
        /****************************************/

        public Boolean code128_enable_marginless_decode = null;
        public Boolean code39_enable_marginless_decode = null;
        public Boolean upc_enable_marginless_decode = null;
        public Boolean i20f5_enable_marginless_decode = null;
    }
    public MarginLess MarginLess = new MarginLess();

    /*
    Undocumented and not available from DataWedge Configuration GUI
    */
    public Boolean trigger_wakeup = null;


    public Bundle getBarcodePluginBundleForSetConfig(boolean resetConfig)
    {
        // Barcode plugin configuration
        Bundle barcodePluginConfig = new Bundle();
        barcodePluginConfig.putString("PLUGIN_NAME", "BARCODE");
        barcodePluginConfig.putString("RESET_CONFIG", resetConfig ? "true" : "false");

        Bundle barcodeProps = new Bundle();

        setupScannerPlugin(barcodeProps);

        barcodePluginConfig.putBundle("PARAM_LIST", barcodeProps);
        return barcodePluginConfig;
    }
    
    private void setupScannerPlugin(Bundle barcodeProps)
    {
        barcodeProps.putString("scanner_input_enabled", scanner_input_enabled ? "true" : "false");

        // Use this for Datawedge < 6.7
        //barcodeProps.putString("scanner_selection", scanner_selection);

        // Use this for Datawedge < 6.7
        //barcodeProps.putString("scanner_selection", "AUTO");
        // Use this for Datawedge >= 6.7
        barcodeProps.putString("scanner_selection_by_identifier",scanner_selection_by_identifier.toString());

        // Setup decoders
        setupDecoders(barcodeProps);

        // Setup parameters associated with decoders
        setupDecodersParams(barcodeProps);

        // Setup UPC/EAN Params
        setupUPC_EANParams(barcodeProps);

        // Setup Reader Params
        setupReaderParams(barcodeProps, false);

        // Setup Scan Params
        setupScanParams(barcodeProps);

        // Multibarcode
        setupMultiBarcode(barcodeProps);

        // Setup undocumented parameters... found after reading
        // the content of the bundle returned by the GET_CONFIG Datawedge intent
        // Uncomment at your own risks
        // setupOtherParameters(barcodeProps);
    }

    private  void setupDecoders(Bundle barcodeProps) {
        if(Decoders.decoder_australian_postal                       != null            ) barcodeProps.putString(   "decoder_australian_postal"                , Decoders.decoder_australian_postal               ? "true":"false");
        if(Decoders.decoder_aztec                                   != null            ) barcodeProps.putString(   "decoder_aztec"                            , Decoders.decoder_aztec                           ? "true":"false");
        if(Decoders.decoder_canadian_postal                         != null            ) barcodeProps.putString(   "decoder_canadian_postal"                  , Decoders.decoder_canadian_postal                 ? "true":"false");
        if(Decoders.decoder_chinese_2of5                            != null            ) barcodeProps.putString(   "decoder_chinese_2of5"                     , Decoders.decoder_chinese_2of5                    ? "true":"false");
        if(Decoders.decoder_codabar                                 != null            ) barcodeProps.putString(   "decoder_codabar"                          , Decoders.decoder_codabar                         ? "true":"false");
        if(Decoders.decoder_code11                                  != null            ) barcodeProps.putString(   "decoder_code11"                           , Decoders.decoder_code11                          ? "true":"false");
        if(Decoders.decoder_code128                                 != null            ) barcodeProps.putString(   "decoder_code128"                          , Decoders.decoder_code128                         ? "true":"false");
        if(Decoders.decoder_code39                                  != null            ) barcodeProps.putString(   "decoder_code39"                           , Decoders.decoder_code39                          ? "true":"false");
        if(Decoders.decoder_code93                                  != null            ) barcodeProps.putString(   "decoder_code93"                           , Decoders.decoder_code93                          ? "true":"false");
        if(Decoders.decoder_composite_ab                            != null            ) barcodeProps.putString(   "decoder_composite_ab"                     , Decoders.decoder_composite_ab                    ? "true":"false");
        if(Decoders.decoder_composite_c                             != null            ) barcodeProps.putString(   "decoder_composite_c"                      , Decoders.decoder_composite_c                     ? "true":"false");
        if(Decoders.decoder_d2of5                                   != null            ) barcodeProps.putString(   "decoder_d2of5"                            , Decoders.decoder_d2of5                           ? "true":"false");
        if(Decoders.decoder_datamatrix                              != null            ) barcodeProps.putString(   "decoder_datamatrix"                       , Decoders.decoder_datamatrix                      ? "true":"false");
        if(Decoders.decoder_dutch_postal                            != null            ) barcodeProps.putString(   "decoder_dutch_postal"                     , Decoders.decoder_dutch_postal                    ? "true":"false");
        if(Decoders.decoder_ean13                                   != null            ) barcodeProps.putString(   "decoder_ean13"                            , Decoders.decoder_ean13                           ? "true":"false");
        if(Decoders.decoder_ean8                                    != null            ) barcodeProps.putString(   "decoder_ean8"                             , Decoders.decoder_ean8                            ? "true":"false");
        if(Decoders.decoder_gs1_databar                             != null            ) barcodeProps.putString(   "decoder_gs1_databar"                      , Decoders.decoder_gs1_databar                     ? "true":"false");
        if(Decoders.decoder_hanxin                                  != null            ) barcodeProps.putString(   "decoder_hanxin"                           , Decoders.decoder_hanxin                          ? "true":"false");
        if(Decoders.decoder_i2of5                                   != null            ) barcodeProps.putString(   "decoder_i2of5"                            , Decoders.decoder_i2of5                           ? "true":"false");
        if(Decoders.decoder_japanese_postal                         != null            ) barcodeProps.putString(   "decoder_japanese_postal"                  , Decoders.decoder_japanese_postal                 ? "true":"false");
        if(Decoders.decoder_korean_3of5                             != null            ) barcodeProps.putString(   "decoder_korean_3of5"                      , Decoders.decoder_korean_3of5                     ? "true":"false");
        if(Decoders.decoder_mailmark                                != null            ) barcodeProps.putString(   "decoder_mailmark"                         , Decoders.decoder_mailmark                        ? "true":"false");
        if(Decoders.decoder_matrix_2of5                             != null            ) barcodeProps.putString(   "decoder_matrix_2of5"                      , Decoders.decoder_matrix_2of5                     ? "true":"false");
        if(Decoders.decoder_maxicode                                != null            ) barcodeProps.putString(   "decoder_maxicode"                         , Decoders.decoder_maxicode                        ? "true":"false");
        if(Decoders.decoder_micropdf                                != null            ) barcodeProps.putString(   "decoder_micropdf"                         , Decoders.decoder_micropdf                        ? "true":"false");
        if(Decoders.decoder_microqr                                 != null            ) barcodeProps.putString(   "decoder_microqr"                          , Decoders.decoder_microqr                         ? "true":"false");
        if(Decoders.decoder_msi                                     != null            ) barcodeProps.putString(   "decoder_msi"                              , Decoders.decoder_msi                             ? "true":"false");
        if(Decoders.decoder_pdf417                                  != null            ) barcodeProps.putString(   "decoder_pdf417"                           , Decoders.decoder_pdf417                          ? "true":"false");
        if(Decoders.decoder_qrcode                                  != null            ) barcodeProps.putString(   "decoder_qrcode"                           , Decoders.decoder_qrcode                          ? "true":"false");
        if(Decoders.decoder_signature                               != null            ) barcodeProps.putString(   "decoder_signature"                        , Decoders.decoder_signature                       ? "true":"false");
        if(Decoders.decoder_tlc39                                   != null            ) barcodeProps.putString(   "decoder_tlc39"                            , Decoders.decoder_tlc39                           ? "true":"false");
        if(Decoders.decoder_trioptic39                              != null            ) barcodeProps.putString(   "decoder_trioptic39"                       , Decoders.decoder_trioptic39                      ? "true":"false");
        if(Decoders.decoder_uk_postal                               != null            ) barcodeProps.putString(   "decoder_uk_postal"                        , Decoders.decoder_uk_postal                       ? "true":"false");
        if(Decoders.decoder_upca                                    != null            ) barcodeProps.putString(   "decoder_upca"                             , Decoders.decoder_upca                            ? "true":"false");
        if(Decoders.decoder_upce0                                   != null            ) barcodeProps.putString(   "decoder_upce0"                            , Decoders.decoder_upce0                           ? "true":"false");
        if(Decoders.decoder_upce1                                   != null            ) barcodeProps.putString(   "decoder_upce1"                            , Decoders.decoder_upce1                           ? "true":"false");
        if(Decoders.decoder_us4state                                != null            ) barcodeProps.putString(   "decoder_us4state"                         , Decoders.decoder_us4state                        ? "true":"false");
        if(Decoders.decoder_usplanet                                != null            ) barcodeProps.putString(   "decoder_usplanet"                         , Decoders.decoder_usplanet                        ? "true":"false");
        if(Decoders.decoder_uspostnet                               != null            ) barcodeProps.putString(   "decoder_uspostnet"                        , Decoders.decoder_uspostnet                       ? "true":"false");
        if(Decoders.decoder_webcode                                 != null            ) barcodeProps.putString(   "decoder_webcode"                          , Decoders.decoder_webcode                         ? "true":"false");
    }

    private  void setupDecodersParams(Bundle barcodeProps)
    {
        if(DecodersParams.decoder_codabar_clsi_editing                    != null   ) barcodeProps.putString(   "decoder_codabar_clsi_editing"             , DecodersParams.decoder_codabar_clsi_editing             ? "true":"false");
        if(DecodersParams.decoder_codabar_length1                         != null   ) barcodeProps.putString(       "decoder_codabar_length1"                  , DecodersParams.decoder_codabar_length1.toString()                  );
        if(DecodersParams.decoder_codabar_length2                         != null   ) barcodeProps.putString(       "decoder_codabar_length2"                  , DecodersParams.decoder_codabar_length2.toString()                  );
        if(DecodersParams.decoder_codabar_notis_editing                   != null   ) barcodeProps.putString(   "decoder_codabar_notis_editing"            , DecodersParams.decoder_codabar_notis_editing            ? "true":"false");
        if(DecodersParams.decoder_codabar_redundancy                      != null   ) barcodeProps.putString(   "decoder_codabar_redundancy"               , DecodersParams.decoder_codabar_redundancy               ? "true":"false");
        if(DecodersParams.decoder_code11_length1                          != null   ) barcodeProps.putString(       "decoder_code11_length1"                   , DecodersParams.decoder_code11_length1.toString()                   );
        if(DecodersParams.decoder_code11_length2                          != null   ) barcodeProps.putString(       "decoder_code11_length2"                   , DecodersParams.decoder_code11_length2.toString()                   );
        if(DecodersParams.decoder_code11_redundancy                       != null   ) barcodeProps.putString(   "decoder_code11_redundancy"                , DecodersParams.decoder_code11_redundancy                ? "true":"false");
        if(DecodersParams.decoder_code11_report_check_digit               != null   ) barcodeProps.putString(   "decoder_code11_report_check_digit"        , DecodersParams.decoder_code11_report_check_digit        ? "true":"false");
        if(DecodersParams.decoder_code11_verify_check_digit               != null   ) barcodeProps.putString(    "decoder_code11_verify_check_digit"        , DecodersParams.decoder_code11_verify_check_digit.toString()        );
        if(DecodersParams.decoder_code128_check_isbt_table                != null   ) barcodeProps.putString(   "decoder_code128_check_isbt_table"         , DecodersParams.decoder_code128_check_isbt_table         ? "true":"false");
        if(DecodersParams.decoder_code128_enable_ean128                   != null   ) barcodeProps.putString(   "decoder_code128_enable_ean128"            , DecodersParams.decoder_code128_enable_ean128            ? "true":"false");
        if(DecodersParams.decoder_code128_enable_isbt128                  != null   ) barcodeProps.putString(   "decoder_code128_enable_isbt128"           , DecodersParams.decoder_code128_enable_isbt128           ? "true":"false");
        if(DecodersParams.decoder_code128_enable_plain                    != null   ) barcodeProps.putString(   "decoder_code128_enable_plain"             , DecodersParams.decoder_code128_enable_plain             ? "true":"false");
        if(DecodersParams.decoder_code128_isbt128_concat_mode             != null   ) barcodeProps.putString(    "decoder_code128_isbt128_concat_mode"      , DecodersParams.decoder_code128_isbt128_concat_mode.toString()      );
        if(DecodersParams.decoder_code128_length1                         != null   ) barcodeProps.putString(       "decoder_code128_length1"                  , DecodersParams.decoder_code128_length1.toString()                  );
        if(DecodersParams.decoder_code128_length2                         != null   ) barcodeProps.putString(       "decoder_code128_length2"                  , DecodersParams.decoder_code128_length2.toString()                  );
        if(DecodersParams.decoder_code128_redundancy                      != null   ) barcodeProps.putString(   "decoder_code128_redundancy"               , DecodersParams.decoder_code128_redundancy               ? "true":"false");
        if(DecodersParams.decoder_code128_security_level                  != null   ) barcodeProps.putString(    "decoder_code128_security_level"           , DecodersParams.decoder_code128_security_level.toString()           );
        if(DecodersParams.code128_ignore_fnc4                             != null   ) barcodeProps.putString(   "code128_ignore_fnc4"                      , DecodersParams.code128_ignore_fnc4                      ? "true":"false");
        if(DecodersParams.decoder_code39_convert_to_code32                != null   ) barcodeProps.putString(   "decoder_code39_convert_to_code32"         , DecodersParams.decoder_code39_convert_to_code32         ? "true":"false");
        if(DecodersParams.decoder_code39_full_ascii                       != null   ) barcodeProps.putString(   "decoder_code39_full_ascii"                , DecodersParams.decoder_code39_full_ascii                ? "true":"false");
        if(DecodersParams.decoder_code39_length1                          != null   ) barcodeProps.putString(       "decoder_code39_length1"                   , DecodersParams.decoder_code39_length1.toString()                   );
        if(DecodersParams.decoder_code39_length2                          != null   ) barcodeProps.putString(       "decoder_code39_length2"                   , DecodersParams.decoder_code39_length2.toString()                   );
        if(DecodersParams.decoder_code39_redundancy                       != null   ) barcodeProps.putString(   "decoder_code39_redundancy"                , DecodersParams.decoder_code39_redundancy                ? "true":"false");
        if(DecodersParams.decoder_code39_report_check_digit               != null   ) barcodeProps.putString(   "decoder_code39_report_check_digit"        , DecodersParams.decoder_code39_report_check_digit        ? "true":"false");
        if(DecodersParams.decoder_code39_report_code32_prefix             != null   ) barcodeProps.putString(   "decoder_code39_report_code32_prefix"      , DecodersParams.decoder_code39_report_code32_prefix      ? "true":"false");
        if(DecodersParams.decoder_code39_security_level                   != null   ) barcodeProps.putString(    "decoder_code39_security_level"            , DecodersParams.decoder_code39_security_level.toString()            );
        if(DecodersParams.decoder_code39_verify_check_digit               != null   ) barcodeProps.putString(   "decoder_code39_verify_check_digit"        , DecodersParams.decoder_code39_verify_check_digit        ? "true":"false");
        if(DecodersParams.decoder_code93_length1                          != null   ) barcodeProps.putString(       "decoder_code93_length1"                   , DecodersParams.decoder_code93_length1.toString()                   );
        if(DecodersParams.decoder_code93_length2                          != null   ) barcodeProps.putString(       "decoder_code93_length2"                   , DecodersParams.decoder_code93_length2.toString()                   );
        if(DecodersParams.decoder_code93_redundancy                       != null   ) barcodeProps.putString(   "decoder_code93_redundancy"                , DecodersParams.decoder_code93_redundancy                ? "true":"false");
        if(DecodersParams.decoder_composite_ab_ucc_link_mode              != null   ) barcodeProps.putString(    "decoder_composite_ab_ucc_link_mode"       , DecodersParams.decoder_composite_ab_ucc_link_mode.toString()       );
        if(DecodersParams.decoder_d2of5_length1                           != null   ) barcodeProps.putString(       "decoder_d2of5_length1"                    , DecodersParams.decoder_d2of5_length1.toString()                    );
        if(DecodersParams.decoder_d2of5_length2                           != null   ) barcodeProps.putString(       "decoder_d2of5_length2"                    , DecodersParams.decoder_d2of5_length2.toString()                    );
        if(DecodersParams.decoder_d2of5_redundancy                        != null   ) barcodeProps.putString(   "decoder_d2of5_redundancy"                 , DecodersParams.decoder_d2of5_redundancy                 ? "true":"false");
        if(DecodersParams.decoder_ean8_convert_to_ean13                   != null   ) barcodeProps.putString(   "decoder_ean8_convert_to_ean13"            , DecodersParams.decoder_ean8_convert_to_ean13            ? "true":"false");
        if(DecodersParams.decoder_gs1_databar_exp                         != null   ) barcodeProps.putString(   "decoder_gs1_databar_exp"                  , DecodersParams.decoder_gs1_databar_exp ? "true" : "false"                  );
        if(DecodersParams.decoder_gs1_databar_lim                         != null   ) barcodeProps.putString(   "decoder_gs1_databar_lim"                  , DecodersParams.decoder_gs1_databar_lim                  ? "true":"false");
        if(DecodersParams.decoder_gs1_lim_security_level                  != null   ) barcodeProps.putString(    "decoder_gs1_lim_security_level"           , DecodersParams.decoder_gs1_lim_security_level.toString()           );
        if(DecodersParams.decoder_hanxin_inverse                          != null   ) barcodeProps.putString(    "decoder_hanxin_inverse"                   , DecodersParams.decoder_hanxin_inverse.toString()                   );
        if(DecodersParams.decoder_i2of5_check_digit                       != null   ) barcodeProps.putString(    "decoder_i2of5_check_digit"                , DecodersParams.decoder_i2of5_check_digit.toString()                );
        if(DecodersParams.decoder_i2of5_length1                           != null   ) barcodeProps.putString(       "decoder_i2of5_length1"                    , DecodersParams.decoder_i2of5_length1.toString()                    );
        if(DecodersParams.decoder_i2of5_length2                           != null   ) barcodeProps.putString(       "decoder_i2of5_length2"                    , DecodersParams.decoder_i2of5_length2.toString()                    );
        if(DecodersParams.decoder_i2of5_redundancy                        != null   ) barcodeProps.putString(   "decoder_i2of5_redundancy"                 , DecodersParams.decoder_i2of5_redundancy                 ? "true":"false");
        if(DecodersParams.decoder_i2of5_report_check_digit                != null   ) barcodeProps.putString(   "decoder_i2of5_report_check_digit"         ,  DecodersParams.decoder_i2of5_report_check_digit ? "true" : "false"        );
        if(DecodersParams.decoder_i2of5_security_level                    != null   ) barcodeProps.putString(    "decoder_i2of5_security_level"             , DecodersParams.decoder_i2of5_security_level.toString()             );
        if(DecodersParams.decoder_i2of5_convert_to_ean13                  != null   ) barcodeProps.putString(   "decoder_i2of5_convert_to_ean13"           , DecodersParams.decoder_i2of5_convert_to_ean13           ? "true":"false");
        if(DecodersParams.decoder_itf14_convert_to_ean13                  != null   ) barcodeProps.putString(   "decoder_itf14_convert_to_ean13"           , DecodersParams.decoder_itf14_convert_to_ean13           ? "true":"false");
        if(DecodersParams.decoder_matrix_2of5_length1                     != null   ) barcodeProps.putString(       "decoder_matrix_2of5_length1"              , DecodersParams.decoder_matrix_2of5_length1.toString()              );
        if(DecodersParams.decoder_matrix_2of5_length2                     != null   ) barcodeProps.putString(       "decoder_matrix_2of5_length2"              , DecodersParams.decoder_matrix_2of5_length2.toString()              );
        if(DecodersParams.decoder_matrix_2of5_redundancy                  != null   ) barcodeProps.putString(   "decoder_matrix_2of5_redundancy"           , DecodersParams.decoder_matrix_2of5_redundancy           ? "true":"false");
        if(DecodersParams.decoder_matrix_2of5_report_check_digit          != null   ) barcodeProps.putString(   "decoder_matrix_2of5_report_check_digit"   , DecodersParams.decoder_matrix_2of5_report_check_digit   ? "true":"false");
        if(DecodersParams.decoder_matrix_2of5_verify_check_digit          != null   ) barcodeProps.putString(   "decoder_matrix_2of5_verify_check_digit"   , DecodersParams.decoder_matrix_2of5_verify_check_digit   ? "true":"false");
        if(DecodersParams.decoder_msi_check_digit                         != null   ) barcodeProps.putString(    "decoder_msi_check_digit"                  , DecodersParams.decoder_msi_check_digit.toString()                  );
        if(DecodersParams.decoder_msi_check_digit_scheme                  != null   ) barcodeProps.putString(    "decoder_msi_check_digit_scheme"           , DecodersParams.decoder_msi_check_digit_scheme.toString()           );
        if(DecodersParams.decoder_msi_length1                             != null   ) barcodeProps.putString(       "decoder_msi_length1"                      , DecodersParams.decoder_msi_length1.toString()                      );
        if(DecodersParams.decoder_msi_length2                             != null   ) barcodeProps.putString(       "decoder_msi_length2"                      , DecodersParams.decoder_msi_length2.toString()                      );
        if(DecodersParams.decoder_msi_redundancy                          != null   ) barcodeProps.putString(   "decoder_msi_redundancy"                   , DecodersParams.decoder_msi_redundancy                   ? "true":"false");
        if(DecodersParams.decoder_msi_report_check_digit                  != null   ) barcodeProps.putString(   "decoder_msi_report_check_digit"           , DecodersParams.decoder_msi_report_check_digit           ? "true":"false");
        if(DecodersParams.decoder_trioptic39_redundancy                   != null   ) barcodeProps.putString(   "decoder_trioptic39_redundancy"            , DecodersParams.decoder_trioptic39_redundancy            ? "true":"false");
        if(DecodersParams.decoder_uk_postal_report_check_digit            != null   ) barcodeProps.putString(   "decoder_uk_postal_report_check_digit"     , DecodersParams.decoder_uk_postal_report_check_digit     ? "true":"false");
        if(DecodersParams.decoder_upca_preamble                           != null   ) barcodeProps.putString(    "decoder_upca_preamble"                    , DecodersParams.decoder_upca_preamble.toString()                    );
        if(DecodersParams.decoder_upca_report_check_digit                 != null   ) barcodeProps.putString(   "decoder_upca_report_check_digit"          , DecodersParams.decoder_upca_report_check_digit          ? "true":"false");
        if(DecodersParams.decoder_upce0_convert_to_upca                   != null   ) barcodeProps.putString(   "decoder_upce0_convert_to_upca"            , DecodersParams.decoder_upce0_convert_to_upca            ? "true":"false");
        if(DecodersParams.decoder_upce0_preamble                          != null   ) barcodeProps.putString(    "decoder_upce0_preamble"                   , DecodersParams.decoder_upce0_preamble.toString()                   );
        if(DecodersParams.decoder_upce0_report_check_digit                != null   ) barcodeProps.putString(   "decoder_upce0_report_check_digit"         , DecodersParams.decoder_upce0_report_check_digit         ? "true":"false");
        if(DecodersParams.decoder_upce1_convert_to_upca                   != null   ) barcodeProps.putString(   "decoder_upce1_convert_to_upca"            , DecodersParams.decoder_upce1_convert_to_upca            ? "true":"false");
        if(DecodersParams.decoder_upce1_preamble                          != null   ) barcodeProps.putString(    "decoder_upce1_preamble"                   , DecodersParams.decoder_upce1_preamble.toString()                   );
        if(DecodersParams.decoder_upce1_report_check_digit                != null   ) barcodeProps.putString(   "decoder_upce1_report_check_digit"         , DecodersParams.decoder_upce1_report_check_digit         ? "true":"false");
        if(DecodersParams.decoder_us4state_fics                           != null   ) barcodeProps.putString(   "decoder_us4state_fics"                    , DecodersParams.decoder_us4state_fics                    ? "true":"false");
        if(DecodersParams.decoder_usplanet_report_check_digit             != null   ) barcodeProps.putString(   "decoder_usplanet_report_check_digit"      , DecodersParams.decoder_usplanet_report_check_digit      ? "true":"false");
   }

    private void setupUPC_EANParams(Bundle barcodeProps)
    {
        if( UpcEan.databar_to_upc_ean != null)
            barcodeProps.putString("databar_to_upc_ean", UpcEan.databar_to_upc_ean ? "true":"false");
        if( UpcEan.upcean_bookland != null)
            barcodeProps.putString("upcean_bookland", UpcEan.upcean_bookland ? "true":"false");
        if( UpcEan.upcean_bookland_format != null)
            barcodeProps.putString("upcean_bookland_format", UpcEan.upcean_bookland_format.toString());
        if( UpcEan.upcean_coupon != null)
            barcodeProps.putString("upcean_coupon", UpcEan.upcean_coupon ? "true":"false");
        if( UpcEan.upcean_coupon_report != null)
            barcodeProps.putString("upcean_coupon_report", UpcEan.upcean_coupon_report.toString());
        if( UpcEan.upcean_ean_zero_extend != null)
            barcodeProps.putString("upcean_ean_zero_extend", UpcEan.upcean_ean_zero_extend ? "true":"false");
        if( UpcEan.upcean_retry_count != null)
            barcodeProps.putString("upcean_retry_count", UpcEan.upcean_retry_count.toString());
        if( UpcEan.upcean_security_level != null)
            barcodeProps.putString("upcean_security_level", UpcEan.upcean_security_level.toString());
        if( UpcEan.upcean_supplemental2 != null)
            barcodeProps.putString("upcean_supplemental2", UpcEan.upcean_supplemental2 ? "true" : "false");
        if( UpcEan.upcean_supplemental5 != null)
            barcodeProps.putString("upcean_supplemental5", UpcEan.upcean_supplemental5 ? "true" : "false");
        if( UpcEan.upcean_supplemental_mode != null)
            barcodeProps.putString("upcean_supplemental_mode", UpcEan.upcean_supplemental_mode.toString());
        if( UpcEan.upcean_linear_decode != null)
            barcodeProps.putString("upcean_linear_decode", UpcEan.upcean_linear_decode ? "true":"false");
        if( UpcEan.upcean_random_weight_check_digit != null)
            barcodeProps.putString("upcean_random_weight_check_digit", UpcEan.upcean_random_weight_check_digit ? "true":"false");
    }

    private void setupReaderParams(Bundle barcodeProps, boolean switchParams) {

        // This parameter is not supported when switching parameters
        if(switchParams == false && ( ReaderParams.aim_mode != null))
            barcodeProps.putString("aim_mode", ReaderParams.aim_mode.toString());

        if( ReaderParams.charset_name != null)
            barcodeProps.putString("charset_name", ReaderParams.charset_name.toString());

        if( ReaderParams.illumination_mode != null)
            barcodeProps.putString("illumination_mode", ReaderParams.illumination_mode.toString());

        if( ReaderParams.inverse_1d_mode != null)
            barcodeProps.putString("inverse_1d_mode", ReaderParams.inverse_1d_mode.toString());

        if( ReaderParams.lcd_mode != null)
            barcodeProps.putString("lcd_mode", ReaderParams.lcd_mode.toString());

        if( ReaderParams.linear_security_level != null)
            barcodeProps.putString("linear_security_level", ReaderParams.linear_security_level.toString());

        if( ReaderParams.low_power_timeout != null)
            barcodeProps.putString("low_power_timeout", Integer.toString(ReaderParams.low_power_timeout));

        if( ReaderParams.picklist != null)
            barcodeProps.putString("picklist", ReaderParams.picklist.toString());

        if( ReaderParams.poor_quality_bcdecode_effort_level != null)
            barcodeProps.putString("poor_quality_bcdecode_effort_level", ReaderParams.poor_quality_bcdecode_effort_level.toString());

        if( ReaderParams.aim_timer != null)
            barcodeProps.putString("aim_timer", Integer.toString(ReaderParams.aim_timer));

        if( ReaderParams.aim_type != null)
            barcodeProps.putString("aim_type", ReaderParams.aim_type.toString());

        if( ReaderParams.beam_timer != null)
            barcodeProps.putString("beam_timer", Integer.toString(ReaderParams.beam_timer));

        // This value must be a multiple of 500
        if( ReaderParams.different_barcode_timeout != null)
        {
            // Let's brute force the multiple of 500
            int remainder = ReaderParams.different_barcode_timeout % 500;
            int different_barcode_timeout = (ReaderParams.different_barcode_timeout / 500) * 500 + (remainder > 250 ? 500 : 0);
            barcodeProps.putString("different_barcode_timeout", Integer.toString(different_barcode_timeout));
        }

        // This value must be a multiple of 500
        if( ReaderParams.same_barcode_timeout != null)
        {
            // Let's brute force the multiple of 500
            int remainder = ReaderParams.same_barcode_timeout % 500;
            int same_barcode_timeout = (ReaderParams.same_barcode_timeout / 500) * 500 + (remainder > 250 ? 500 : 0);
            barcodeProps.putString("same_barcode_timeout", Integer.toString(same_barcode_timeout));
        }

        if( ReaderParams.scanning_mode != null)
            barcodeProps.putString("scanning_mode", ReaderParams.scanning_mode.toString());

        if( ReaderParams.oneD_marginless_decode_effort_level != null)
        {
            barcodeProps.putString("1d_marginless_decode_effort_level", ReaderParams.oneD_marginless_decode_effort_level.toString());
        }
    }

    private void setupScanParams(Bundle barcodeProps)
    {
        if( ScanParams.code_id_type != null)
            barcodeProps.putString("code_id_type", ScanParams.code_id_type.toString());

        if( ScanParams.decode_haptic_feedback != null)
            barcodeProps.putString("decode_haptic_feedback", ScanParams.decode_haptic_feedback ? "true":"false");

        if( ScanParams.decode_audio_feedback_uri != null)
            barcodeProps.putString("decode_audio_feedback_uri", ScanParams.decode_audio_feedback_uri.toString());

        if( ScanParams.decoding_led_feedback != null)
            barcodeProps.putString("decoding_led_feedback", ScanParams.decoding_led_feedback ? "true":"false");

        if( ScanParams.good_decode_led_timer != null)
            barcodeProps.putString("good_decode_led_timer", ScanParams.good_decode_led_timer.toString());

        if( ScanParams.volume_slider_type != null)
            barcodeProps.putString("volume_slider_type", ScanParams.volume_slider_type.toString());
    }

    private void setupMultiBarcode(Bundle barcodeProps)
    {
        if( MultiBarcode.multi_barcode_count != null)
            barcodeProps.putString("multi_barcode_count", MultiBarcode.multi_barcode_count.toString());
    }

    private void setupOtherParameters(Bundle barcodeProps)
    {
        if( trigger_wakeup != null)
            barcodeProps.putString("trigger-wakeup", trigger_wakeup ? "true" : "false");

        if( MarginLess.code128_enable_marginless_decode != null)
            barcodeProps.putString("decoding_led_feedback", MarginLess.code128_enable_marginless_decode ? "true":"false");

        if( MarginLess.code39_enable_marginless_decode != null)
            barcodeProps.putString("code39_enable_marginless_decode", MarginLess.code39_enable_marginless_decode ? "true":"false");

        if( MarginLess.upc_enable_marginless_decode != null)
            barcodeProps.putString("upc_enable_marginless_decode", MarginLess.upc_enable_marginless_decode ? "true":"false");

        if( MarginLess.i20f5_enable_marginless_decode != null)
            barcodeProps.putString("i20f5_enable_marginless_decode", MarginLess.i20f5_enable_marginless_decode ? "true":"false");
    }

    /**
     * Use this method if you want to switch between two knowns parameters
     * @param configToBeCompared
     * @return
     */
    public Bundle getBarcodePluginBundleForSwitchParams(PluginScanner configToBeCompared)
    {
        // Pass everything to the bundle
        Bundle barcodeProps = new Bundle();

        // Setup Reader Params
        setupReaderParams(barcodeProps, true);

        return barcodeProps;
    }

    /**
     * Use this method if you want to force all parameters to be switched
     * @return
     */
    public Bundle getBarcodePluginBundleForSwitchParams()
    {
        // Pass everything to the bundle
        Bundle barcodeProps = new Bundle();

        // Setup decoders
        setupDecoders(barcodeProps);

        // Setup parameters associated with decoders
        setupDecodersParams(barcodeProps);

        // Setup UPC/EAN Params
        setupUPC_EANParams(barcodeProps);

        // Setup Reader Params
        setupReaderParams(barcodeProps, true);

        // Setup Scan Params
        setupScanParams(barcodeProps);

        // Multibarcode
        setupMultiBarcode(barcodeProps);

        // Other params
        setupOtherParameters(barcodeProps);


        return barcodeProps;
    }
}

