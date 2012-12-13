
package hu.iboard.coandco.datamagic.webservice.managementreport;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the hu.iboard.coandco.datamagic.webservice.managementreport package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _ManagementReport_QNAME = new QName("http://schemas.datacontract.org/2004/07/SZLA", "ManagementReport");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _GetMachineNameResponseGetMachineNameResult_QNAME = new QName("http://tempuri.org/", "GetMachineNameResult");
    private final static QName _GetLastExceptionResponseGetLastExceptionResult_QNAME = new QName("http://tempuri.org/", "GetLastExceptionResult");
    private final static QName _GetSessionIdDbPass_QNAME = new QName("http://tempuri.org/", "dbPass");
    private final static QName _GetSessionIdDataBase_QNAME = new QName("http://tempuri.org/", "dataBase");
    private final static QName _GetSessionIdCegUnikazon_QNAME = new QName("http://tempuri.org/", "cegUnikazon");
    private final static QName _GetSessionIdDbPort_QNAME = new QName("http://tempuri.org/", "dbPort");
    private final static QName _GetSessionIdDbServer_QNAME = new QName("http://tempuri.org/", "dbServer");
    private final static QName _GetSessionIdDolgozoPass_QNAME = new QName("http://tempuri.org/", "dolgozoPass");
    private final static QName _GetSessionIdDbUser_QNAME = new QName("http://tempuri.org/", "dbUser");
    private final static QName _SendGridMessage_QNAME = new QName("http://tempuri.org/", "message");
    private final static QName _SendGridEndpoint_QNAME = new QName("http://tempuri.org/", "endpoint");
    private final static QName _SendGridGridDescriptor_QNAME = new QName("http://tempuri.org/", "gridDescriptor");
    private final static QName _GetGridValueResponseGetGridValueResult_QNAME = new QName("http://tempuri.org/", "GetGridValueResult");
    private final static QName _GetManagementReportResponseGetManagementReportResult_QNAME = new QName("http://tempuri.org/", "GetManagementReportResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hu.iboard.coandco.datamagic.webservice.managementreport
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMachineNameResponse }
     * 
     */
    public GetMachineNameResponse createGetMachineNameResponse() {
        return new GetMachineNameResponse();
    }

    /**
     * Create an instance of {@link GetLastException }
     * 
     */
    public GetLastException createGetLastException() {
        return new GetLastException();
    }

    /**
     * Create an instance of {@link GetSessionId }
     * 
     */
    public GetSessionId createGetSessionId() {
        return new GetSessionId();
    }

    /**
     * Create an instance of {@link SendGrid }
     * 
     */
    public SendGrid createSendGrid() {
        return new SendGrid();
    }

    /**
     * Create an instance of {@link GetGridValueResponse }
     * 
     */
    public GetGridValueResponse createGetGridValueResponse() {
        return new GetGridValueResponse();
    }

    /**
     * Create an instance of {@link SendGridResponse }
     * 
     */
    public SendGridResponse createSendGridResponse() {
        return new SendGridResponse();
    }

    /**
     * Create an instance of {@link GetAruCountResponse }
     * 
     */
    public GetAruCountResponse createGetAruCountResponse() {
        return new GetAruCountResponse();
    }

    /**
     * Create an instance of {@link GetLastExceptionResponse }
     * 
     */
    public GetLastExceptionResponse createGetLastExceptionResponse() {
        return new GetLastExceptionResponse();
    }

    /**
     * Create an instance of {@link GetAruCount }
     * 
     */
    public GetAruCount createGetAruCount() {
        return new GetAruCount();
    }

    /**
     * Create an instance of {@link IsValidSessionResponse }
     * 
     */
    public IsValidSessionResponse createIsValidSessionResponse() {
        return new IsValidSessionResponse();
    }

    /**
     * Create an instance of {@link GetGridValue }
     * 
     */
    public GetGridValue createGetGridValue() {
        return new GetGridValue();
    }

    /**
     * Create an instance of {@link ManagementReport }
     * 
     */
    public ManagementReport createManagementReport() {
        return new ManagementReport();
    }

    /**
     * Create an instance of {@link GetDateTimeNowResponse }
     * 
     */
    public GetDateTimeNowResponse createGetDateTimeNowResponse() {
        return new GetDateTimeNowResponse();
    }

    /**
     * Create an instance of {@link GetSessionIdResponse }
     * 
     */
    public GetSessionIdResponse createGetSessionIdResponse() {
        return new GetSessionIdResponse();
    }

    /**
     * Create an instance of {@link GetMachineName }
     * 
     */
    public GetMachineName createGetMachineName() {
        return new GetMachineName();
    }

    /**
     * Create an instance of {@link GetDateTimeNow }
     * 
     */
    public GetDateTimeNow createGetDateTimeNow() {
        return new GetDateTimeNow();
    }

    /**
     * Create an instance of {@link GetManagementReport }
     * 
     */
    public GetManagementReport createGetManagementReport() {
        return new GetManagementReport();
    }

    /**
     * Create an instance of {@link GetManagementReportResponse }
     * 
     */
    public GetManagementReportResponse createGetManagementReportResponse() {
        return new GetManagementReportResponse();
    }

    /**
     * Create an instance of {@link IsValidSession }
     * 
     */
    public IsValidSession createIsValidSession() {
        return new IsValidSession();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManagementReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/SZLA", name = "ManagementReport")
    public JAXBElement<ManagementReport> createManagementReport(ManagementReport value) {
        return new JAXBElement<ManagementReport>(_ManagementReport_QNAME, ManagementReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetMachineNameResult", scope = GetMachineNameResponse.class)
    public JAXBElement<String> createGetMachineNameResponseGetMachineNameResult(String value) {
        return new JAXBElement<String>(_GetMachineNameResponseGetMachineNameResult_QNAME, String.class, GetMachineNameResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetLastExceptionResult", scope = GetLastExceptionResponse.class)
    public JAXBElement<String> createGetLastExceptionResponseGetLastExceptionResult(String value) {
        return new JAXBElement<String>(_GetLastExceptionResponseGetLastExceptionResult_QNAME, String.class, GetLastExceptionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dbPass", scope = GetSessionId.class)
    public JAXBElement<String> createGetSessionIdDbPass(String value) {
        return new JAXBElement<String>(_GetSessionIdDbPass_QNAME, String.class, GetSessionId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dataBase", scope = GetSessionId.class)
    public JAXBElement<String> createGetSessionIdDataBase(String value) {
        return new JAXBElement<String>(_GetSessionIdDataBase_QNAME, String.class, GetSessionId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "cegUnikazon", scope = GetSessionId.class)
    public JAXBElement<String> createGetSessionIdCegUnikazon(String value) {
        return new JAXBElement<String>(_GetSessionIdCegUnikazon_QNAME, String.class, GetSessionId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dbPort", scope = GetSessionId.class)
    public JAXBElement<String> createGetSessionIdDbPort(String value) {
        return new JAXBElement<String>(_GetSessionIdDbPort_QNAME, String.class, GetSessionId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dbServer", scope = GetSessionId.class)
    public JAXBElement<String> createGetSessionIdDbServer(String value) {
        return new JAXBElement<String>(_GetSessionIdDbServer_QNAME, String.class, GetSessionId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dolgozoPass", scope = GetSessionId.class)
    public JAXBElement<String> createGetSessionIdDolgozoPass(String value) {
        return new JAXBElement<String>(_GetSessionIdDolgozoPass_QNAME, String.class, GetSessionId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dbUser", scope = GetSessionId.class)
    public JAXBElement<String> createGetSessionIdDbUser(String value) {
        return new JAXBElement<String>(_GetSessionIdDbUser_QNAME, String.class, GetSessionId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "message", scope = SendGrid.class)
    public JAXBElement<String> createSendGridMessage(String value) {
        return new JAXBElement<String>(_SendGridMessage_QNAME, String.class, SendGrid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "endpoint", scope = SendGrid.class)
    public JAXBElement<String> createSendGridEndpoint(String value) {
        return new JAXBElement<String>(_SendGridEndpoint_QNAME, String.class, SendGrid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "gridDescriptor", scope = SendGrid.class)
    public JAXBElement<String> createSendGridGridDescriptor(String value) {
        return new JAXBElement<String>(_SendGridGridDescriptor_QNAME, String.class, SendGrid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetGridValueResult", scope = GetGridValueResponse.class)
    public JAXBElement<Object> createGetGridValueResponseGetGridValueResult(Object value) {
        return new JAXBElement<Object>(_GetGridValueResponseGetGridValueResult_QNAME, Object.class, GetGridValueResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManagementReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetManagementReportResult", scope = GetManagementReportResponse.class)
    public JAXBElement<ManagementReport> createGetManagementReportResponseGetManagementReportResult(ManagementReport value) {
        return new JAXBElement<ManagementReport>(_GetManagementReportResponseGetManagementReportResult_QNAME, ManagementReport.class, GetManagementReportResponse.class, value);
    }

}
