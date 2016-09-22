<?php require "head.php" ?>
	<title>Kontakt</title>
</head>
<body>
<?php include "navigation.php" ?>
    <!-- Kontaktfeld, bestehend aus Eingabefeldern. (Konventionen der Browser)-->

    <div class="contactform">
        <div class="formular">
            <form>
                <label>Name:</label><br />
                <input type="text" pattern="^[a-zA-Z]*" required value="" name="surname"/><br />
                <label>Vorname:</label><br />
                <input type="text" pattern="^[a-zA-Z]*" required value="" name="forename"/><br />
                <label>PLZ:</label><br />
                <input type="text" pattern="^[0-9]{5}$" required value="" name="postcode" /><br />
                <label>Ort:</label><br />
                <input type="text" pattern="^[a-zA-Z]*" required="" value="" name="city" /><br />
                <!-- Aufzählung aller Länder -->
                <label>Land:</label><br />
                <select required name="country">
					<option value="">Land</option>
                    <option value="AF">Afghanistan</option>
                    <option value="EG">Ägypten</option>
                    <option value="AX">Aland</option>
                    <option value="AL">Albanien</option>
                    <option value="DZ">Algerien</option>
                    <option value="AS">Amerikanisch-Samoa</option>
                    <option value="VI">Amerikanische Jungferninseln</option>
                    <option value="AD">Andorra</option>
                    <option value="AO">Angola</option>
                    <option value="AI">Anguilla</option>
                    <option value="AQ">Antarktis</option>
                    <option value="AG">Antigua und Barbuda</option>
                    <option value="GQ">Äquatorialguinea</option>
                    <option value="AR">Argentinien</option>
                    <option value="AM">Armenien</option>
                    <option value="AW">Aruba</option>
                    <option value="AC">Ascension</option>
                    <option value="AZ">Aserbaidschan</option>
                    <option value="ET">Äthiopien</option>
                    <option value="AU">Australien</option>
                    <option value="BS">Bahamas</option>
                    <option value="BH">Bahrain</option>
                    <option value="BD">Bangladesch</option>
                    <option value="BB">Barbados</option>
                    <option value="BE">Belgien</option>
                    <option value="BZ">Belize</option>
                    <option value="BJ">Benin</option>
                    <option value="BM">Bermuda</option>
                    <option value="BT">Bhutan</option>
                    <option value="BO">Bolivien</option>
                    <option value="BA">Bosnien und Herzegowina</option>
                    <option value="BW">Botswana</option>
                    <option value="BV">Bouvetinsel</option>
                    <option value="BR">Brasilien</option>
                    <option value="BN">Brunei</option>
                    <option value="BG">Bulgarien</option>
                    <option value="BF">Burkina Faso</option>
                    <option value="BI">Burundi</option>
                    <option value="CL">Chile</option>
                    <option value="CN">China</option>
                    <option value="CK">Cookinseln</option>
                    <option value="CR">Costa Rica</option>
                    <option value="CI">Cote d'Ivoire</option>
                    <option value="DK">Dänemark</option>
                    <option selected value="DE">Deutschland</option>
                    <option value="DG">Diego Garcia</option>
                    <option value="DM">Dominica</option>
                    <option value="DO">Dominikanische Republik</option>
                    <option value="DJ">Dschibuti</option>
                    <option value="EC">Ecuador</option>
                    <option value="SV">El Salvador</option>
                    <option value="ER">Eritrea</option>
                    <option value="EE">Estland</option>
                    <option value="EU">Europäische Union</option>
                    <option value="FK">Falklandinseln</option>
                    <option value="FO">Färöer</option>
                    <option value="FJ">Fidschi</option>
                    <option value="FI">Finnland</option>
                    <option value="FR">Frankreich</option>
                    <option value="GF">Französisch-Guayana</option>
                    <option value="PF">Französisch-Polynesien</option>
                    <option value="GA">Gabun</option>
                    <option value="GM">Gambia</option>
                    <option value="GE">Georgien</option>
                    <option value="GH">Ghana</option>
                    <option value="GI">Gibraltar</option>
                    <option value="GD">Grenada</option>
                    <option value="GR">Griechenland</option>
                    <option value="GL">Grönland</option>
                    <option value="GB">Großbritannien</option>
                    <option value="CP">Guadeloupe</option>
                    <option value="GU">Guam</option>
                    <option value="GT">Guatemala</option>
                    <option value="GG">Guernsey</option>
                    <option value="GN">Guinea</option>
                    <option value="GW">Guinea-Bissau</option>
                    <option value="GY">Guyana</option>
                    <option value="HT">Haiti</option>
                    <option value="HM">Heard und McDonaldinseln</option>
                    <option value="HN">Honduras</option>
                    <option value="HK">Hongkong</option>
                    <option value="IN">Indien</option>
                    <option value="ID">Indonesien</option>
                    <option value="IQ">Irak</option>
                    <option value="IR">Iran</option>
                    <option value="IE">Irland</option>
                    <option value="IS">Island</option>
                    <option value="IL">Israel</option>
                    <option value="IT">Italien</option>
                    <option value="JM">Jamaika</option>
                    <option value="JP">Japan</option>
                    <option value="YE">Jemen</option>
                    <option value="JE">Jersey</option>
                    <option value="JO">Jordanien</option>
                    <option value="KY">Kaimaninseln</option>
                    <option value="KH">Kambodscha</option>
                    <option value="CM">Kamerun</option>
                    <option value="CA">Kanada</option>
                    <option value="IC">Kanarische Inseln</option>
                    <option value="CV">Kap Verde</option>
                    <option value="KZ">Kasachstan</option>
                    <option value="QA">Katar</option>
                    <option value="KE">Kenia</option>
                    <option value="KG">Kirgisistan</option>
                    <option value="KI">Kiribati</option>
                    <option value="CC">Kokosinseln</option>
                    <option value="CO">Kolumbien</option>
                    <option value="KM">Komoren</option>
                    <option value="CG">Kongo</option>
                    <option value="HR">Kroatien</option>
                    <option value="CU">Kuba</option>
                    <option value="KW">Kuwait</option>
                    <option value="LA">Laos</option>
                    <option value="LS">Lesotho</option>
                    <option value="LV">Lettland</option>
                    <option value="LB">Libanon</option>
                    <option value="LR">Liberia</option>
                    <option value="LY">Libyen</option>
                    <option value="LI">Liechtenstein</option>
                    <option value="LT">Litauen</option>
                    <option value="LU">Luxemburg</option>
                    <option value="MO">Macao</option>
                    <option value="MG">Madagaskar</option>
                    <option value="MW">Malawi</option>
                    <option value="MY">Malaysia</option>
                    <option value="MV">Malediven</option>
                    <option value="ML">Mali</option>
                    <option value="MT">Malta</option>
                    <option value="MA">Marokko</option>
                    <option value="MH">Marshallinseln</option>
                    <option value="MQ">Martinique</option>
                    <option value="MR">Mauretanien</option>
                    <option value="MU">Mauritius</option>
                    <option value="YT">Mayotte</option>
                    <option value="MK">Mazedonien</option>
                    <option value="MX">Mexiko</option>
                    <option value="FM">Mikronesien</option>
                    <option value="MD">Moldawien</option>
                    <option value="MC">Monaco</option>
                    <option value="MN">Mongolei</option>
                    <option value="MS">Montserrat</option>
                    <option value="MZ">Mosambik</option>
                    <option value="MM">Myanmar</option>
                    <option value="NA">Namibia</option>
                    <option value="NR">Nauru</option>
                    <option value="NP">Nepal</option>
                    <option value="NC">Neukaledonien</option>
                    <option value="NZ">Neuseeland</option>
                    <option value="NT">Neutrale Zone</option>
                    <option value="NI">Nicaragua</option>
                    <option value="NL">Niederlande</option>
                    <option value="AN">Niederländische Antillen</option>
                    <option value="NE">Niger</option>
                    <option value="NG">Nigeria</option>
                    <option value="NU">Niue</option>
                    <option value="KP">Nordkorea</option>
                    <option value="MP">Nördliche Marianen</option>
                    <option value="NF">Norfolkinsel</option>
                    <option value="NO">Norwegen</option>
                    <option value="OM">Oman</option>
                    <option value="AT">Österreich</option>
                    <option value="PK">Pakistan</option>
                    <option value="PS">Palästina</option>
                    <option value="PW">Palau</option>
                    <option value="PA">Panama</option>
                    <option value="PG">Papua-Neuguinea</option>
                    <option value="PY">Paraguay</option>
                    <option value="PE">Peru</option>
                    <option value="PH">Philippinen</option>
                    <option value="PN">Pitcairninseln</option>
                    <option value="PL">Polen</option>
                    <option value="PT">Portugal</option>
                    <option value="PR">Puerto Rico</option>
                    <option value="RE">Réunion</option>
                    <option value="RW">Ruanda</option>
                    <option value="RO">Rumänien</option>
                    <option value="RU">Russische Föderation</option>
                    <option value="SB">Salomonen</option>
                    <option value="ZM">Sambia</option>
                    <option value="WS">Samoa</option>
                    <option value="SM">San Marino</option>
                    <option value="ST">São Tomé und Príncipe</option>
                    <option value="SA">Saudi-Arabien</option>
                    <option value="SE">Schweden</option>
                    <option value="CH">Schweiz</option>
                    <option value="SN">Senegal</option>
                    <option value="CS">Serbien und Montenegro</option>
                    <option value="SC">Seychellen</option>
                    <option value="SL">Sierra Leone</option>
                    <option value="ZW">Simbabwe</option>
                    <option value="SG">Singapur</option>
                    <option value="SK">Slowakei</option>
                    <option value="SI">Slowenien</option>
                    <option value="SO">Somalia</option>
                    <option value="ES">Spanien</option>
                    <option value="LK">Sri Lanka</option>
                    <option value="SH">St. Helena</option>
                    <option value="KN">St. Kitts und Nevis</option>
                    <option value="LC">St. Lucia</option>
                    <option value="PM">St. Pierre und Miquelon</option>
                    <option value="VC">St. Vincent/Grenadinen (GB)</option>
                    <option value="ZA">Südafrika, Republik</option>
                    <option value="SD">Sudan</option>
                    <option value="KR">Südkorea</option>
                    <option value="SR">Suriname</option>
                    <option value="SJ">Svalbard und Jan Mayen</option>
                    <option value="SZ">Swasiland</option>
                    <option value="SY">Syrien</option>
                    <option value="TJ">Tadschikistan</option>
                    <option value="TW">Taiwan</option>
                    <option value="TZ">Tansania</option>
                    <option value="TH">Thailand</option>
                    <option value="TL">Timor-Leste</option>
                    <option value="TG">Togo</option>
                    <option value="TK">Tokelau</option>
                    <option value="TO">Tonga</option>
                    <option value="TT">Trinidad und Tobago</option>
                    <option value="TA">Tristan da Cunha</option>
                    <option value="TD">Tschad</option>
                    <option value="CZ">Tschechische Republik</option>
                    <option value="TN">Tunesien</option>
                    <option value="TR">Türkei</option>
                    <option value="TM">Turkmenistan</option>
                    <option value="TC">Turks- und Caicosinseln</option>
                    <option value="TV">Tuvalu</option>
                    <option value="UG">Uganda</option>
                    <option value="UA">Ukraine</option>
                    <option value="HU">Ungarn</option>
                    <option value="UY">Uruguay</option>
                    <option value="UZ">Usbekistan</option>
                    <option value="VU">Vanuatu</option>
                    <option value="VA">Vatikanstadt</option>
                    <option value="VE">Venezuela</option>
                    <option value="AE">Vereinigte Arabische Emirate</option>
                    <option value="US">Vereinigte Staaten von Amerika</option>
                    <option value="VN">Vietnam</option>
                    <option value="WF">Wallis und Futuna</option>
                    <option value="CX">Weihnachtsinsel</option>
                    <option value="BY">Weißrussland</option>
                    <option value="EH">Westsahara</option>
                    <option value="CF">Zentralafrikanische Republik</option>
                    <option value="CY">Zypern</option>
                </select><br />
                <label>E-Mail:</label><br />
                <input type="email" pattern='[a-zA-Z]*@[a-zA-Z]*\.[a-zA-Z]*' required value="" name="email"/><br />
                <label>Nachricht:</label><br />
                <textarea required name="message" rows="10"></textarea><br />
                <button name="btnContact">Absenden</button>
            </form>
        </div>
    </div>
<?php require "foot.php" ?>