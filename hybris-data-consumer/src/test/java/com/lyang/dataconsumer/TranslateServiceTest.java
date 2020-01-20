package com.lyang.dataconsumer;

import com.lyang.dataconsumer.translator.DruidSqlParserRouter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * FileName: TranslateServiceTest
 * Author:   lujy7
 * Date:     2020/1/20 10:05
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ConsumerApplication.class})
public class TranslateServiceTest {

    @Autowired
    private DruidSqlParserRouter druidSqlParserRouter;

    @Test
    public void parseDeleteSql(){
        druidSqlParserRouter.process("delete deplyd where id > 5 and b=2 and c='12'");
        //druidSqlParserRouter.process("DELETE FROM props WHERE ITEMPK=11810424751030");
    }

    @Test
    public void parseInsertSql(){
        //druidSqlParserRouter.process("insert into deplyd (idname,typename) values ((123, 'bad'),(ceil(234), 'bad'))");
        druidSqlParserRouter.process("INSERT INTO payload ( hjmpTS,PK,createdTS,modifiedTS,TypePkString,p_zordernumber,p_zpayloadcontent,p_ztype ) VALUES (0,8921103366925,'2019-12-27 19:03:40.078','2019-12-27 19:03:40.078',8796184772690,'4292432665','ApiOwner=93API&ApiKey=fc898bb132504cd285e7c723118661d22dcff2ac5f58416ea83759e&RequestBody=%7B%22order%22%3A%7B%22order_currency%22%3A%22INR%22%2C%22ship_pincode%22%3A%22560032%22%2C%22bill_phone1%22%3A%229945959075%22%2C%22ship_country%22%3A%22India%22%2C%22bill_name%22%3A%22kagdsfb+dhsauhuh%22%2C%22bill_city%22%3A%22Bangalore%22%2C%22uniqueKey%22%3A%224292432665%22%2C%22order_no%22%3A%224292432665%22%2C%22order_amount%22%3A%2278794.000%22%2C%22ship_email1%22%3A%22philip24%40gmail.com%22%2C%22bill_address1%22%3A%22safhdiuh%22%2C%22bill_email1%22%3A%22philip24%40gmail.com%22%2C%22order_location%22%3A%22PSW%22%2C%22ship_phone1%22%3A%229945959075%22%2C%22bill_address2%22%3A%22dsfuihsdah%22%2C%22custTinNo%22%3A%22%22%2C%22order_date%22%3A%2212%2F01%2F2019+09%3A14%3A33%22%2C%22ship_city%22%3A%22Bangalore%22%2C%22status%22%3A%22Confirmed%22%2C%22customer_name%22%3A%22kagdsfb+dhsauhuh%22%2C%22shipping_charges%22%3A%220.000%22%2C%22ship_address2%22%3A%22dsfuihsdah%22%2C%22ship_address1%22%3A%22safhdiuh%22%2C%22items%22%3A%5B%7B%22order_qty%22%3A%222%22%2C%22sku%22%3A%22F0D500CTIN%22%2C%22unit_price%22%3A%2238898.000%22%2C%22lineno%22%3A%220%22%2C%22mode%22%3A%22wms%22%7D%2C%7B%22order_qty%22%3A%222%22%2C%22sku%22%3A%22PEACE-OF-MIND-WARR%22%2C%22unit_price%22%3A%22499.000%22%2C%22lineno%22%3A%221%22%2C%22mode%22%3A%22wms%22%7D%5D%2C%22bill_state%22%3A%22Karnataka%22%2C%22bill_pincode%22%3A%22560032%22%2C%22bill_country%22%3A%22India%22%2C%22ship_state%22%3A%22Karnataka%22%2C%22order_type%22%3A%22Prepaid%22%7D%7DCall Vinculum have an error, The responseCode is:11, The responseMessage is:Invalid API credentials, The uniqueKey is:, The requestKey is:, The outputKey is:, The errorDesc is:, Order:4292432665','Vinculum')");
    }

    @Test
    public void parseUpdateSql(){
        druidSqlParserRouter.process("UPDATE processes SET hjmpTS = 16079 ,modifiedTS='2019-12-27 19:03:53.11',p_state=8796098822235 WHERE PK = 9877039316990");
    }

}
