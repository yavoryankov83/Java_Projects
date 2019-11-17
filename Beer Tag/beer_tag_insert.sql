create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint username_authority
        unique (username, authority),
    constraint users__fk
        foreign key (username) references users (username)
);

INSERT INTO beer_tag.authorities (username, authority) VALUES ('bella', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('diana', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('dimitar', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('galia', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('nadya', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('pesho', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('rosen', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('stanislav', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('tihomira', 'ROLE_ADMIN');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('tihomira', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('todor', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('user', 'ROLE_ADMIN');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('yavor', 'ROLE_ADMIN');
INSERT INTO beer_tag.authorities (username, authority) VALUES ('yavor', 'ROLE_USER');
create table beers
(
    id          int auto_increment
        primary key,
    name        varchar(50) charset utf8 not null,
    abv         double(2, 1)             not null,
    description text                     null,
    photo       longblob                 null,
    style_id    int                      null,
    country_id  int                      null,
    brewery_id  int                      null,
    constraint beers_breweries_fk
        foreign key (brewery_id) references breweries (id),
    constraint beers_breweries_id_fk
        foreign key (brewery_id) references breweries (id),
    constraint beers_origin_countries_fk
        foreign key (country_id) references origin_countries (id),
    constraint beers_styles_fk
        foreign key (style_id) references styles (id)
);

INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (1, 'Heineken', 5, 'Bright and deep golden in color and endowed with a warm malty fragrance that’s superbly countered with a balanced hop aroma. The pleasing malt flavor yields accents of fruit and yeast that are perfectly matched by a distinct', 0x68747470733A2F2F7777772E6472696E6B73757065726D61726B65742E636F6D2F6D656469612F636174616C6F672F70726F647563742F63616368652F312F696D6167652F39646637386561623333353235643038643665356662386432373133366539352F682F652F6865696E656B656E2D626565722D3333306D6C5F74656D705F332E6A7067, 8, 60, 18);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (2, 'Budweiser', 4.5, null, 0x68747470733A2F2F6175737472616C69616E6C6971756F72737570706C696572732E636F6D2E61752F77702D636F6E74656E742F75706C6F6164732F323031392F30312F6275647765697365722D626F74746C652E6A7067, 8, 97, 19);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (3, 'Guinness Irish Wheat', 5.3, 'Guinness Irish Wheat is brewed with Guinness ale yeast and 100% Irish wheat. This is a clean crisp', 0x68747470733A2F2F69322E77702E636F6D2F6C6F766562656C666173742E636F2E756B2F77702D636F6E74656E742F75706C6F6164732F323031372F30332F4775696E6E6573735F49726973685F57686561745F464F505F554B2E706E673F726573697A653D33303030253243333030302673736C3D31, 15, 41, 9);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (4, 'Corona Light', 4.1, 'Corona Light is a pilsner-style lager with a uniquely refreshing taste—brewed for outstanding light flavor with a crisp clean finish. Its pleasant', 0x68747470733A2F2F7777772E676F6B61726D61636166652E636F6D2F77702D636F6E74656E742F75706C6F6164732F323031382F30362F63713564616D2E7765625F2E313238302E31323830312E6A706567, 12, 55, 17);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (5, 'Blue Moon Harvest Pumpkin Wheat', 5.7, 'Nationally recognized as a seasonal favorite Harvest Pumpkin Wheat Ale returns with the autumn flavors we’ve all grown to love. Crafted with real pumpkin and harvest spices such as cinnamon', 0x68747470733A2F2F656E637279707465642D74626E302E677374617469632E636F6D2F696D616765733F713D74626E3A414E6439476351516352425958626647466272544E643468784E53726D3172544175575564357A4E7A4F34736A58734E7A7A667064324166, 15, 97, 20);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (6, 'Zagorka Special', 5, 'It has a sparkling golden color medium carbonation and thick creamy foam. Nice grainy and hops aroma', 0x68747470733A2F2F63646E2E6D696E6962617264656C69766572792E636F6D2F70726F64756374732F3132303437342F70726F647563742F3131313739353131372E2E6A7067, 8, 13, 16);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (7, 'Heineken Zero', 5.4, 'Light golden in color Heineken 0.0 has a fruity', 0x68747470733A2F2F6432367675636B31716F3239756B2E636C6F756466726F6E742E6E65742F75706C6F6164732F70726F64756374732F39346236616366303237363366636363316133393834366234393234343535302E6A7067, 11, 60, 18);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (8, 'Corona Extra', 4.5, 'With a refreshing smooth taste balanced between heavier European imports and lighter domestic beer', 0x68747470733A2F2F63646E2E73686F706C6967687473706565642E636F6D2F73686F70732F3630393233382F66696C65732F323933353436352F636F726F6E612D65787472612D6162762D34352D32342D6F7A2E6A7067, 8, 55, 17);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (9, 'Coors Light', 4.2, 'Very light beer', 0x68747470733A2F2F6469676974616C636F6E74656E742E6170692E746573636F2E636F6D2F76312F6D656469612F6768732F736E617073686F74696D61676568616E646C65725F313731383830343030312F383739396666383833656236343830663337373635353265613664643332366136306136646631323834343064303830393464616133616438626461393930392F30353031303033383435353533312F736E617073686F74696D61676568616E646C65725F313731383830343030312E6A7065673F683D35343026773D353430, 8, 97, 22);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (10, 'Bud Light', 4.2, 'Bud Light is brewed using a blend of premium aroma hop varieties both American-grown and imported', 0x68747470733A2F2F70726F6475637473302E696D6769782E6472697A6C792E636F6D2F63692D6275642D6C696768742D313936393964636433653735393165332E706E673F6175746F3D666F726D6174253243636F6D707265737326666D3D6A70656726713D3230, 8, 97, 19);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (11, 'Guinness Extra Stout', 5.6, 'This is it the one that started it all. Crafted to perfection for over 200 years', 0x68747470733A2F2F70726F6475637473312E696D6769782E6472697A6C792E636F6D2F63692D6775696E6E6573732D65787472612D73746F75742D326162633338386365396333626236312E6A7065673F6175746F3D666F726D6174253243636F6D707265737326666D3D6A70656726713D3230, 14, 41, 9);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (12, 'Amstel Light', 3.5, 'The official “Beer of the Burger,” Amstel Light is deep gold in color and brewed with a unique mixture of barley and hops. Its full, never-diluted flavor sets it apart from other light beers. 95 calories never tasted so good!', 0x68747470733A2F2F63646E322E626967636F6D6D657263652E636F6D2F736572766572353530302F74706263327336352F70726F64756374732F3231382F696D616765732F3234332F616D7374656C5F6C6967687431326F7A5F5F32353433362E313335303432303237332E313238302E313238302E6A70673F633D32, 9, 60, 18);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (13, 'Buckler', 0.5, 'Buckler is widely recognized as the best of the non-alcoholic pilsner-style beers. When poured this bright golden beer gives off aromas of warm malt that fill the senses. Its pleasant carbonation produces a true beer head. The taste is well balanced with slight residual notes of sweetness and hints of fruit.', 0x68747470733A2F2F70726F6475637473332E696D6769782E6472697A6C792E636F6D2F63692D6275636B6C65722D6E6F6E2D616C636F686F6C69632D626565722D613031623865623839313861656161632E6A7065673F6175746F3D666F726D6174253243636F6D707265737326666D3D6A70656726713D3230, 11, 97, 18);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (14, 'Kamenitza Light', 4.4, 'Produced in Bulgaria under the supervision of Belgian Brewmasters.', 0x68747470733A2F2F7777772E6E6F76696E6974652E636F6D2F6D656469612F696D616765732F323031322D30362F70686F746F5F766572796269675F3134303432382E6A7067, 9, 13, 7);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (15, 'Heineken Light', 3.5, 'Heineken Light has an irresistibly smooth character. From its beautiful balance of malt and hops to its mild aroma that teases the nose with hidden notes of fruit this is a beer that’s bright and bubbly. Light golden in color', 0x68747470733A2F2F63646E2E73686F706C6967687473706565642E636F6D2F73686F70732F3631313431332F66696C65732F373434393533392F6865696E656B656E2D627265776572792D6865696E656B656E2D6C696768742D36706B2D626F74746C65732E6A7067, 8, 60, 18);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (16, 'Blue Moon Honey Wheat', 5.4, 'Honey Wheat is crafted with clover honey balanced by a hint of citrus  and tastes best with none other than the original Blue Moon orange garnish. ', 0x68747470733A2F2F7265732E636C6F7564696E6172792E636F6D2F72617465626565722F696D6167652F75706C6F61642F645F44656661756C745F426565725F71717276376B2E706E672C665F6175746F2F626565725F3635373434, 7, 97, 20);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (17, 'Astika', 5, '', 0x68747470733A2F2F692E70696E696D672E636F6D2F6F726967696E616C732F65652F36322F33392F65653632333966623466383966623064646264636663643030623538356263362E6A7067, 9, 13, 7);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (18, 'Guinness Foreign Extra Stout', 7.5, 'Foreign Extra Stout is brewed with generous hops and roasted barley for a bittersweet balance and full-flavored natural bite. Developed over 200 years ago for global export from Ireland', 0x687474703A2F2F7777772E7765736861796F2E636F6D2F77702D636F6E74656E742F75706C6F6164732F323031362F30382F4775696E6E6573732D466F726569676E2E6A7067, 14, 41, 9);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (19, 'Zagorka Gold', 5, '', 0x687474703A2F2F7777772E67756964656465736269657265732E636F6D2F70686F746F732F343636352D7A61676F726B612D676F6C642E6A7067, 8, 13, 16);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (20, 'Stella Artois', 5.2, 'Stella Artois is a classic Belgian pilsner golden in color with a floral', 0x68747470733A2F2F63646E2E696E666C75656E737465722E636F6D2F6D656469612F70726F647563742F696D6167652F7374656C6C612D6172746F69732D7072656D69756D2D6C616765722D626565722D6E72622D3636306D6C2E6A70672E373530783735305F7138357373305F70726F67726573736976652E6A7067, 12, 9, 23);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (21, 'Beck''s Premium Light', 2.3, '', 0x68747470733A2F2F63646E2E73686F706966792E636F6D2F732F66696C65732F312F303031382F393233382F343832352F70726F64756374732F3137373337332D4265636B735F5072656D6965725F4C696768745F6772616E64652E6A70673F763D31353639333435393439, 8, 33, 1);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (22, 'Carlsberg Pilsner', 4.6, 'A refreshing and uniquely characterful international premium Pilsener with a malty backbone and balanced bitterness. Aka: probably the best beer in the world.', 0x68747470733A2F2F7777772E6472696E6B73757065726D61726B65742E636F6D2F6D656469612F636174616C6F672F70726F647563742F63616368652F312F696D6167652F39646637386561623333353235643038643665356662386432373133366539352F632F612F6361726C73626572672D70696C736E65722D3333306D6C2E6A7067, 12, 60, 6);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (23, 'Guinness Draught Stout', 4.2, 'Brewed since 1759 in Dublin Ireland', 0x68747470733A2F2F63646E322E626967636F6D6D657263652E636F6D2F736572766572353530302F74706263327336352F70726F64756374732F3235362F696D616765732F3238332F6775696E65737364726175676874313170326F7A5F5F39383738322E313335313030353536352E313238302E313238302E6A70673F633D32, 14, 41, 9);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (24, 'Smithwick’s Red Ale', 4.5, 'Smithwick’s is a ruby red brew with a gentle hop bitterness and a sweet malt finish. Subtle aromas of caramel', 0x68747470733A2F2F70726F6475637473312E696D6769782E6472697A6C792E636F6D2F63692D736D6974687769636B732D616C652D663831643838363236376663636238332E706E673F6175746F3D666F726D6174253243636F6D707265737326666D3D6A70656726713D3230, 13, 41, 9);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (25, 'Burgasko pivo', 4.4, '', 0x68747470733A2F2F73636F6E74656E742D667278352D312E63646E696E7374616772616D2E636F6D2F762F7435312E323838352D31352F7368302E30382F6533352F70363430783634302F33303935333937395F3137333134363738363733353432345F333438313031303036393239363930363234305F6E2E6A70673F5F6E635F68743D73636F6E74656E742D667278352D312E63646E696E7374616772616D2E636F6D266F683D3437316533623437613766373737633031333162653333643664326231616230266F653D35444644334241382669675F63616368655F6B65793D4D5463334F5451794D6A67324E6A6B324D7A67794E4451324E512533442533442E32, 8, 13, 7);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (26, 'Shumensko Special', 5.2, '', 0x68747470733A2F2F66696E646267666F6F642E636F6D2F6F6E6C696E6573746F72652F77702D636F6E74656E742F75706C6F6164732F323031372F31302F7368756D656E736B6F2E6A7067, 8, 13, 6);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (27, 'Beck''s Dark', 4.8, 'Beck’s Dark is rich and smooth with a lingering  slightly sweet aftertaste. The deep ebony color is the result of specially roasting the barley malt. ', 0x687474703A2F2F7777772E62656572696E66696E6974792E636F6D2F77702D636F6E74656E742F75706C6F6164732F6265636B732D6461726B2D626565722E6A7067, 4, 33, 1);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (28, 'Shumensko Dark', 5.5, '', 0x687474703A2F2F6D656E2E686F746E6577732E62672F7374617469632F75706C6F6164732F74696E796D63652F6776696E65612F6E696E6B6F2F6E696E6F2F6262622F7461746F2F68616E6B2F6D6F6F64792F7368756D656E736B6F5F6461726B5F426F74746C652E6A7067, 8, 13, 6);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (29, 'Tuborg', 4.6, '', 0x68747470733A2F2F62696762617272656C2E636F2E6E7A2F636F6E74656E742F696D616765732F7468756D62732F303032313831372E6A706567, 12, 60, 6);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (31, 'Staropramen Unfiltered', 5, '', 0x68747470733A2F2F70726576696577732E31323372662E636F6D2F696D616765732F646172696F732F646172696F73313530362F646172696F733135303630303131382F34313833303737352D7072656D69756D2D756E66696C74657265642D737461726F7072616D656E2D626565722D69736F6C617465642D6F6E2D77686974652E6A7067, 9, 24, 10);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (32, 'Staropramen Dark', 4.4, '', 0x68747470733A2F2F6D2E656261672E62672F656E2F70726F647563742F3135333231382F696D616765732F302F383030, 4, 24, 10);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (33, 'Staropramen Lager', 5, '', 0x68747470733A2F2F7777772E6F6361646F2E636F6D2F70726F64756374496D616765732F3330332F3330333237353031315F305F363430783634302E6A70673F6964656E7469666965723D6666386537323834306636623635326163643537313465346136363465313232, 8, 24, 10);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (34, 'Regent Lemon', 0.5, '', 0x68747470733A2F2F7777772E6472696E6B73757065726D61726B65742E636F6D2F6D656469612F636174616C6F672F70726F647563742F63616368652F312F696D6167652F363030782F30343065633039623165333564663133393433333838376139376461613636662F622F6F2F626F68656D69612D726567656E742D6461726B2D6C616765722D696D706F727465642D637A6563682D6461726B2D6C616765722D3530306D6C2D626F74746C652E6A7067, 5, 24, 2);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (35, 'Bolten-Ur Alt', 4.8, '', 0x68747470733A2F2F7777772E626965722D756E6976657273756D2E64652F66696C6561646D696E2F42696572446174656E2F4269657242696C6465722F626F6C74656E5F75725F616C742E6A7067, 1, 33, 3);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (36, 'Budweiser Budvar Free', 0.5, 'The taste of a proper lager from Budweis but without alcohol.', 0x68747470733A2F2F63646E2E77656273686F706170702E636F6D2F73686F70732F3231363535342F66696C65732F3137393635313137342F6275647765697365722D6275647661722D6275647765697365722D6275647661722D667265652D616C636F686F6C2D6672652E6A7067, 11, 24, 4);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (37, 'Budweiser Budvar Classic', 4, 'The traditional Czech pale beer made of Žatecký hop.', 0x687474703A2F2F7777772E7370697A61726E6961706F646C797361676F72612E706C2F6D6F64756C792F736B6C65702F5573657246696C65732F6269672F3335312F2D2F4275647765697365724275647661723130436C61737369632E6A7067, 6, 24, 4);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (38, 'Budweiser Budvar Dark', 4.7, 'Dark lager with distinct, bitterish taste and subtle tones.', 0x68747470733A2F2F7777772E74686562656C6769616E62656572636F6D70616E792E636F6D2F77702D636F6E74656E742F75706C6F6164732F323031382F30392F6275647765697365722D6275647661722D312E706E67, 4, 24, 4);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (39, 'Cantillon Lou Pepe Pure Kriek', 5, 'Fresh sour cherries (krieken) in two year old Lambiek. ', 0x68747470733A2F2F73746F72652E62656C6769616E73686F702E636F6D2F333534302D6C617267655F64656661756C742F63616E74696C6C6F6E2D6C6F752D706570652D6B7269656B2D352D33346C2D762E6A7067, 5, 9, 5);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (40, 'Krombacher Wheat', 5.3, '', 0x68747470733A2F2F7777772E62656572736F666575726F70652E636F2E756B2F696D616765732F70726F647563742F6C2F4B726F6D6261636865725765697A656E3233303131392E6A70673F743D31353631313333333833, 15, 33, 8);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (41, 'Newcastle Brown Ale', 4.7, 'Brewed by Heineken, this simple, nutty beer shows earthy notes and slight bitterness, with an extremely smooth finish.', 0x68747470733A2F2F6469676974616C636F6E74656E742E6170692E746573636F2E636F6D2F76312F6D656469612F6768732F736E617073686F74696D61676568616E646C65725F313033313835323036342F646238306132626433356465653735316432336365333437613765343632656631313261613132626337636633613235656666313265303233643237646632622F30353031303131383331373834312F736E617073686F74696D61676568616E646C65725F313033313835323036342E6A7065673F683D35343026773D353430, 3, 96, 18);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (42, 'Famosa Malt Lager', 5, '', 0x68747470733A2F2F7374617469632E76696E65706169722E636F6D2F77702D636F6E74656E742F75706C6F6164732F323031372F30342F626565722D322D66616D6F73612E6A7067, 10, 97, 11);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (43, 'Rogue American Amber Ale', 5.1, 'Tawny amber in color with a coffee aroma and tight head. ', 0x68747470733A2F2F7777772E77697368626565722E636F6D2F3830362D6C617267655F64656661756C742F726F6775652D616D65726963616E2D616D6265722D616C652D3335352D6D6C2D35362E6A7067, 2, 97, 12);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (44, 'Weihenstephan Light Wheat Beer', 2.6, 'It impresses the connoisseur with its golden-yellow colour and its fine poured white foam. ', 0x68747470733A2F2F6D656469612E64616E6D7572706879732E636F6D2E61752F646D6F2F70726F647563742F3734343735322D312E706E67, 9, 33, 13);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (45, 'Weihenstephan Non-Alcoholic Original Helles', 0.5, 'The pleasant spicy hops note and the full light and sweet flavor, with a slight bitterness, makes it an ideal companion for snacks, hearty salads or simply a refreshment on hot days. ', 0x68747470733A2F2F7777772E6C63626F2E636F6D2F636F6E74656E742F64616D2F6C63626F2F70726F64756374732F3037353239312E6A70672F6A63723A636F6E74656E742F72656E646974696F6E732F63713564616D2E7765622E313238302E313238302E6A706567, 11, 33, 13);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (46, 'Weihenstephan Wheat Beer', 5.4, 'Wheat beer with its fine-poured white foam, smells of cloves and impresses consumers with its refreshing banana flavor. ', 0x68747470733A2F2F7265732E636C6F7564696E6172792E636F6D2F7361756365792F696D6167652F75706C6F61642F76313439373033353139342F72376C706F75716464766E7067667876753174782E6A7067, 15, 33, 13);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (47, 'Tuborg Guld Malt', 5.8, '', 0x68747470733A2F2F7777772E626576636F2E646B2F6D656469612F636174616C6F672F70726F647563742F63616368652F312F696D6167652F343830783438302F30646332643033666532313766386338333832393439363837326166323461302F672F752F67756C645F7475626F72675F33335F636C2E5F355F365F33305F666C61736B65722E706E67, 10, 60, 6);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (48, 'Tuborg Gold', 5, '', 0x687474703A2F2F736F6B616B626173696D657968616E652E636F6D2F77702D636F6E74656E742F75706C6F6164732F323031382F30352F7475626F72675F676F6C645F62795F7475726B7475626F72672D64343174326B612E6A7067, 6, 60, 6);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (49, 'Kronenbourg 1664', 5.5, 'Kronenbourg 1664 is the most sold French beer in the world and the market leader for high-end premium beers.', 0x68747470733A2F2F7777772E746F74616C77696E652E636F6D2F64796E616D69632F343930782F6D656469612F7379735F6D61737465722F74776D6D656469612F6834312F6839302F383830323839323434373737342E706E67, 8, 32, 14);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (50, 'Ariana Lager', 4.5, '', 0x68747470733A2F2F7062732E7477696D672E636F6D2F6D656469612F4338314B4F655756304141714B5A512E6A7067, 8, 13, 16);
INSERT INTO beer_tag.beers (id, name, abv, description, photo, style_id, country_id, brewery_id) VALUES (73, 'Zagorka IPA', 0.6, '', 0x68747470733A2F2F646F62726F746F74756B2E62672F77702D636F6E74656E742F75706C6F6164732F323031392F30352F5A61676F726B615F4D61795F31322D6D696E2E706E67, 1, 13, 37);
create table beers_tags
(
    beer_id int not null,
    tag_id  int not null,
    primary key (beer_id, tag_id),
    constraint beers_tags_beer_id_fk
        foreign key (beer_id) references beers (id),
    constraint beers_tags_tag_id_fk
        foreign key (tag_id) references tags (id)
);

INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (1, 34);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (1, 37);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (1, 39);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (1, 70);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (2, 11);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (2, 32);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (2, 43);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (2, 71);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (2, 72);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (3, 8);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (3, 10);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (4, 16);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (4, 73);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (5, 46);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (5, 59);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (6, 49);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (7, 56);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (7, 60);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (8, 7);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (8, 19);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (8, 34);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (8, 73);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (9, 5);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (9, 29);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (9, 39);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (10, 37);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (10, 40);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (10, 60);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (11, 4);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (11, 45);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (12, 13);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (12, 38);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (14, 15);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (14, 20);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (14, 22);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (15, 1);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (15, 30);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (16, 26);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (16, 54);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (17, 35);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (17, 52);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (18, 48);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (18, 57);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (20, 35);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (20, 36);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (21, 27);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (22, 27);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (22, 37);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (23, 28);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (23, 32);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (23, 58);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (24, 33);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (25, 30);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (25, 38);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (26, 46);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (27, 41);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (28, 44);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (28, 55);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (29, 51);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (29, 57);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (31, 7);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (31, 10);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (31, 14);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (32, 15);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (32, 21);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (33, 31);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (34, 49);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (34, 50);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (35, 41);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (36, 52);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (37, 3);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (37, 25);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (37, 48);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (38, 23);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (39, 14);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (39, 45);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (40, 29);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (40, 67);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (42, 3);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (42, 53);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (43, 24);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (44, 6);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (44, 9);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (45, 32);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (45, 42);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (46, 44);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (46, 47);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (47, 12);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (47, 24);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (47, 34);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (48, 31);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (48, 33);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (49, 5);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (49, 22);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (49, 33);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (50, 11);
INSERT INTO beer_tag.beers_tags (beer_id, tag_id) VALUES (73, 76);
create table breweries
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null,
    constraint breweries_name_uindex
        unique (name)
);

INSERT INTO beer_tag.breweries (id, name) VALUES (32, 'ALEALE');
INSERT INTO beer_tag.breweries (id, name) VALUES (25, 'AMSTEL AD');
INSERT INTO beer_tag.breweries (id, name) VALUES (23, 'Anheuser–Busch InBev');
INSERT INTO beer_tag.breweries (id, name) VALUES (24, 'Astika AD');
INSERT INTO beer_tag.breweries (id, name) VALUES (33, 'Baaaaaaaa');
INSERT INTO beer_tag.breweries (id, name) VALUES (1, 'Beck''s Brewery');
INSERT INTO beer_tag.breweries (id, name) VALUES (20, 'Blue Moon Brewing Co.');
INSERT INTO beer_tag.breweries (id, name) VALUES (2, 'Bohemia Regent');
INSERT INTO beer_tag.breweries (id, name) VALUES (3, 'Bolten Brewery');
INSERT INTO beer_tag.breweries (id, name) VALUES (34, 'Brewery');
INSERT INTO beer_tag.breweries (id, name) VALUES (35, 'Breweryjdjdj');
INSERT INTO beer_tag.breweries (id, name) VALUES (36, 'Brewwww');
INSERT INTO beer_tag.breweries (id, name) VALUES (4, 'Budvar');
INSERT INTO beer_tag.breweries (id, name) VALUES (19, 'Budweiser Brewery Experience');
INSERT INTO beer_tag.breweries (id, name) VALUES (5, 'Cantillon');
INSERT INTO beer_tag.breweries (id, name) VALUES (6, 'Carlsberg Group');
INSERT INTO beer_tag.breweries (id, name) VALUES (11, 'Cervecería Centro Americana');
INSERT INTO beer_tag.breweries (id, name) VALUES (22, 'Coors Brewing Company');
INSERT INTO beer_tag.breweries (id, name) VALUES (17, 'Corona Brewery');
INSERT INTO beer_tag.breweries (id, name) VALUES (21, 'Guinness Brewery');
INSERT INTO beer_tag.breweries (id, name) VALUES (18, 'Heineken International');
INSERT INTO beer_tag.breweries (id, name) VALUES (7, 'Kamenitza');
INSERT INTO beer_tag.breweries (id, name) VALUES (8, 'Krombacher Brauerei');
INSERT INTO beer_tag.breweries (id, name) VALUES (14, 'Kronenbourg Brewery');
INSERT INTO beer_tag.breweries (id, name) VALUES (12, 'Rogue Ales');
INSERT INTO beer_tag.breweries (id, name) VALUES (9, 'St. James Gate Brewery');
INSERT INTO beer_tag.breweries (id, name) VALUES (10, 'Staropramen');
INSERT INTO beer_tag.breweries (id, name) VALUES (13, 'Weihenstephan Brewery');
INSERT INTO beer_tag.breweries (id, name) VALUES (15, 'Wild Beer Co');
INSERT INTO beer_tag.breweries (id, name) VALUES (16, 'Zagorka');
INSERT INTO beer_tag.breweries (id, name) VALUES (37, 'Zagorka AD');
create table origin_countries
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null,
    constraint origin_countries_name_uindex
        unique (name)
);

INSERT INTO beer_tag.origin_countries (id, name) VALUES (1, 'ALBANIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (2, 'ANGOLA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (3, 'ARGENTINA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (4, 'ARMENIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (5, 'AUSTRALIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (6, 'AUSTRIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (7, 'AZERBAIJAN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (8, 'BELARUS');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (9, 'BELGIUM');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (10, 'BOLIVIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (11, 'BOSNIA_AND_HERZEGOVINA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (12, 'BRAZIL');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (13, 'BULGARIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (14, 'CAMBODIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (15, 'CANADA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (16, 'CAPE_VERDE');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (17, 'CARIBBEAN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (18, 'CENTRAL_AMERICA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (19, 'CHILE');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (20, 'CHINA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (21, 'COLOMBIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (22, 'COSTA_RICA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (23, 'CROATIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (24, 'CZECH_REPUBLIC');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (25, 'DENMARK');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (26, 'EGYPT');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (27, 'ENGLAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (28, 'ERITREA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (29, 'ESTONIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (30, 'ETHIOPIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (31, 'FINLAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (32, 'FRANCE');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (33, 'GERMANY');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (34, 'GREECE');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (35, 'HONG_KONG');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (36, 'HUNGARY');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (37, 'ICELAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (38, 'INDIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (39, 'INDONESIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (40, 'IRAN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (41, 'IRELAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (42, 'ISRAEL');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (43, 'ITALY');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (44, 'JAPAN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (45, 'JORDAN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (46, 'KAZAKHSTAN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (47, 'KENYA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (48, 'KOREA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (49, 'LATVIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (50, 'LEBANON');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (51, 'LITHUANIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (52, 'LUXEMBOURG');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (53, 'MALAYSIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (54, 'MALTA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (55, 'MEXICO');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (56, 'MIDDLE_EAST');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (57, 'MONTENEGRO');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (58, 'MOROCCO');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (59, 'MYANMAR');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (60, 'NETHERLANDS');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (61, 'NEW_ZEALAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (62, 'NIGERIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (64, 'NORTHERN_IRELAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (63, 'NORTH_KOREA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (65, 'NORWAY');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (66, 'PAKISTAN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (67, 'PANAMA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (68, 'PERU');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (69, 'PHILIPPINES');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (70, 'POLAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (71, 'PORTUGAL');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (72, 'QUEBEC');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (73, 'ROMANIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (74, 'RUSSIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (75, 'SCOTLAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (76, 'SERBIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (77, 'SINGAPORE');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (78, 'SLOVAKIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (79, 'SLOVENIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (80, 'SOUTH_AFRICA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (81, 'SOUTH_KOREA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (82, 'SPAIN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (83, 'SRI_LANKA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (84, 'STATE_OF_PALESTINE');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (85, 'SURINAME');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (86, 'SWEDEN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (87, 'SWITZERLAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (88, 'SYRIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (89, 'TAIWAN');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (90, 'TANZANIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (91, 'TASMANIA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (92, 'THAILAND');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (93, 'TIBET');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (94, 'TURKEY');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (95, 'UKRAINE');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (96, 'UNITED_KINGDOM');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (97, 'UNITED_STATES');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (98, 'VENEZUELA');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (99, 'VIETNAM');
INSERT INTO beer_tag.origin_countries (id, name) VALUES (100, 'WALES');
create table ratings
(
    id      int auto_increment
        primary key,
    value   int(10) not null,
    user_id int     null,
    beer_id int     null,
    constraint ratings_beers_id_fk
        foreign key (beer_id) references beers (id),
    constraint ratings_users_id_fk
        foreign key (user_id) references users (id)
);

INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (18, 5, 48, 1);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (19, 8, 48, 2);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (20, 10, 48, 8);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (21, 3, 48, 31);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (22, 6, 48, 44);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (23, 3, 46, 4);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (24, 6, 46, 40);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (25, 7, 46, 10);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (26, 9, 46, 49);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (27, 4, 46, 47);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (28, 6, 7, 23);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (29, 7, 7, 9);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (30, 10, 7, 37);
INSERT INTO beer_tag.ratings (id, value, user_id, beer_id) VALUES (31, 3, 48, 73);
create table status_values
(
    id           int auto_increment
        primary key,
    status_value varchar(20) not null
);

INSERT INTO beer_tag.status_values (id, status_value) VALUES (1, 'I want it!');
INSERT INTO beer_tag.status_values (id, status_value) VALUES (2, 'I already drank it!');
create table statuses
(
    id              int auto_increment
        primary key,
    status_value_id int not null,
    beer_id         int null,
    user_id         int null,
    constraint statuses_beers_id_fk
        foreign key (beer_id) references beers (id),
    constraint statuses_status_values_id_fk
        foreign key (status_value_id) references status_values (id),
    constraint statuses_users_id_fk
        foreign key (user_id) references users (id)
);

INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (7, 1, 1, 48);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (8, 2, 2, 48);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (9, 2, 8, 48);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (10, 1, 31, 48);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (11, 1, 44, 48);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (12, 2, 4, 46);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (13, 1, 40, 46);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (14, 2, 10, 46);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (15, 1, 49, 46);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (16, 2, 47, 46);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (17, 1, 23, 7);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (18, 2, 9, 7);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (19, 1, 37, 7);
INSERT INTO beer_tag.statuses (id, status_value_id, beer_id, user_id) VALUES (20, 2, 73, 48);
create table styles
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null,
    constraint styles_name_uindex
        unique (name)
);

INSERT INTO beer_tag.styles (id, name) VALUES (1, 'ALE');
INSERT INTO beer_tag.styles (id, name) VALUES (2, 'AMBER');
INSERT INTO beer_tag.styles (id, name) VALUES (3, 'BROWN');
INSERT INTO beer_tag.styles (id, name) VALUES (4, 'DARK');
INSERT INTO beer_tag.styles (id, name) VALUES (5, 'FRUIT');
INSERT INTO beer_tag.styles (id, name) VALUES (6, 'GOLDEN');
INSERT INTO beer_tag.styles (id, name) VALUES (7, 'HONEY');
INSERT INTO beer_tag.styles (id, name) VALUES (8, 'LAGER');
INSERT INTO beer_tag.styles (id, name) VALUES (9, 'LIGHT');
INSERT INTO beer_tag.styles (id, name) VALUES (10, 'MALT');
INSERT INTO beer_tag.styles (id, name) VALUES (11, 'NON_ALCOHOLIC');
INSERT INTO beer_tag.styles (id, name) VALUES (12, 'PILSNER');
INSERT INTO beer_tag.styles (id, name) VALUES (13, 'RED');
INSERT INTO beer_tag.styles (id, name) VALUES (14, 'STOUT');
INSERT INTO beer_tag.styles (id, name) VALUES (15, 'WHEAT');
create table tags
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null,
    constraint tags_name_uindex
        unique (name)
);

INSERT INTO beer_tag.tags (id, name) VALUES (19, 'Ambrosial');
INSERT INTO beer_tag.tags (id, name) VALUES (22, 'Aromatic beer');
INSERT INTO beer_tag.tags (id, name) VALUES (23, 'Balanced taste');
INSERT INTO beer_tag.tags (id, name) VALUES (20, 'Best-tasting');
INSERT INTO beer_tag.tags (id, name) VALUES (24, 'Bitter');
INSERT INTO beer_tag.tags (id, name) VALUES (11, 'Blissed out');
INSERT INTO beer_tag.tags (id, name) VALUES (59, 'Classic beer');
INSERT INTO beer_tag.tags (id, name) VALUES (30, 'Classic taste');
INSERT INTO beer_tag.tags (id, name) VALUES (67, 'Collest');
INSERT INTO beer_tag.tags (id, name) VALUES (68, 'CollestEver');
INSERT INTO beer_tag.tags (id, name) VALUES (18, 'Cool taste');
INSERT INTO beer_tag.tags (id, name) VALUES (72, 'Coolest beer');
INSERT INTO beer_tag.tags (id, name) VALUES (75, 'Coolest ever');
INSERT INTO beer_tag.tags (id, name) VALUES (16, 'Creamy foam');
INSERT INTO beer_tag.tags (id, name) VALUES (58, 'Dark-colour');
INSERT INTO beer_tag.tags (id, name) VALUES (43, 'Delicate beer');
INSERT INTO beer_tag.tags (id, name) VALUES (73, 'Delicious');
INSERT INTO beer_tag.tags (id, name) VALUES (5, 'Delighted ');
INSERT INTO beer_tag.tags (id, name) VALUES (42, 'Delightful');
INSERT INTO beer_tag.tags (id, name) VALUES (37, 'Divine');
INSERT INTO beer_tag.tags (id, name) VALUES (44, 'Easy-drinking');
INSERT INTO beer_tag.tags (id, name) VALUES (7, 'Enjoy it!');
INSERT INTO beer_tag.tags (id, name) VALUES (25, 'Exceptional beer');
INSERT INTO beer_tag.tags (id, name) VALUES (33, 'Exotic');
INSERT INTO beer_tag.tags (id, name) VALUES (27, 'Finest taste');
INSERT INTO beer_tag.tags (id, name) VALUES (36, 'Flavorful');
INSERT INTO beer_tag.tags (id, name) VALUES (34, 'Fresh');
INSERT INTO beer_tag.tags (id, name) VALUES (17, 'Freshness');
INSERT INTO beer_tag.tags (id, name) VALUES (26, 'Fruity');
INSERT INTO beer_tag.tags (id, name) VALUES (32, 'Fun-to-drink');
INSERT INTO beer_tag.tags (id, name) VALUES (15, 'Golden colour');
INSERT INTO beer_tag.tags (id, name) VALUES (1, 'Good taste');
INSERT INTO beer_tag.tags (id, name) VALUES (35, 'Harmonious taste');
INSERT INTO beer_tag.tags (id, name) VALUES (28, 'High-quality');
INSERT INTO beer_tag.tags (id, name) VALUES (2, 'Incredible beer');
INSERT INTO beer_tag.tags (id, name) VALUES (41, 'Intense');
INSERT INTO beer_tag.tags (id, name) VALUES (38, 'Invigorating');
INSERT INTO beer_tag.tags (id, name) VALUES (29, 'Legendary');
INSERT INTO beer_tag.tags (id, name) VALUES (3, 'Magic');
INSERT INTO beer_tag.tags (id, name) VALUES (10, 'Medium-sweet');
INSERT INTO beer_tag.tags (id, name) VALUES (21, 'Mellow');
INSERT INTO beer_tag.tags (id, name) VALUES (46, 'Mild taste');
INSERT INTO beer_tag.tags (id, name) VALUES (31, 'Most popular');
INSERT INTO beer_tag.tags (id, name) VALUES (71, 'My favorite');
INSERT INTO beer_tag.tags (id, name) VALUES (47, 'Naturally-flavored');
INSERT INTO beer_tag.tags (id, name) VALUES (76, 'Nice');
INSERT INTO beer_tag.tags (id, name) VALUES (6, 'Nice colour');
INSERT INTO beer_tag.tags (id, name) VALUES (53, 'Old-fashioned');
INSERT INTO beer_tag.tags (id, name) VALUES (52, 'Original taste');
INSERT INTO beer_tag.tags (id, name) VALUES (54, 'Pleasant');
INSERT INTO beer_tag.tags (id, name) VALUES (13, 'Pot-valiant');
INSERT INTO beer_tag.tags (id, name) VALUES (56, 'Preferred');
INSERT INTO beer_tag.tags (id, name) VALUES (12, 'Premium beer');
INSERT INTO beer_tag.tags (id, name) VALUES (60, 'Refreshing taste');
INSERT INTO beer_tag.tags (id, name) VALUES (55, 'Revitalizing sense');
INSERT INTO beer_tag.tags (id, name) VALUES (57, 'Rich taste');
INSERT INTO beer_tag.tags (id, name) VALUES (40, 'Silky taste');
INSERT INTO beer_tag.tags (id, name) VALUES (70, 'Smell nice');
INSERT INTO beer_tag.tags (id, name) VALUES (49, 'Smooth taste');
INSERT INTO beer_tag.tags (id, name) VALUES (8, 'Soft taste');
INSERT INTO beer_tag.tags (id, name) VALUES (4, 'Strong');
INSERT INTO beer_tag.tags (id, name) VALUES (9, 'Stylish');
INSERT INTO beer_tag.tags (id, name) VALUES (39, 'Superior beer');
INSERT INTO beer_tag.tags (id, name) VALUES (50, 'Surprisingly good');
INSERT INTO beer_tag.tags (id, name) VALUES (45, 'Traditional beer');
INSERT INTO beer_tag.tags (id, name) VALUES (14, 'Unbelievable joy');
INSERT INTO beer_tag.tags (id, name) VALUES (51, 'Unique');
INSERT INTO beer_tag.tags (id, name) VALUES (48, 'Well-balanced');
create table users
(
    id                   int auto_increment
        primary key,
    username             varchar(50)  not null,
    password             varchar(68)  not null,
    enabled              tinyint      not null,
    email                varchar(50)  null,
    photo                longblob     null,
    hash                 varchar(100) null,
    value                varchar(100) null,
    passwordConfirmation varchar(68)  null,
    constraint users_email_uindex
        unique (email),
    constraint users_username_uindex
        unique (username)
);

INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (7, 'tihomira', '$2a$10$1xJ8j09gzrRcAhWGQMVScueYd8EFrU0Xgc7qVDigy8dcbeyNZiWbG', 1, 'tihomira@abv.bg', 0x68747470733A2F2F737065636B79626F792E636F6D2F77702D636F6E74656E742F75706C6F6164732F323031312F31302F6361726963617475726561727432382E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (38, 'rosen', '$2a$10$.rc8xrzpA.yAW3g3gBhXAuXh99qxbD9Cm9sE3ecVsgrJTOCynNDRS', 1, 'rosen@abv.bg', 0x687474703A2F2F7777772E77656972646C796F64642E636F6D2F77702D636F6E74656E742F75706C6F6164732F6266695F7468756D622F3330383930305F313230383334393339345F6C617267652D327764776B6D613531613733726F39746C39726269382E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (39, 'bella', '$2a$10$vcon.Tgusj2oxZ2Vu3GF5Oop6YaciBp/qczTEjU0nlluG8S2xs2ru', 1, 'bella@abv.bg', 0x68747470733A2F2F6E616C647A67726170686963732E6E65742F77702D636F6E74656E742F75706C6F6164732F323031312F30312F31312D416E67656C696E612D4A6F6C69652E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (40, 'todor', '$2a$10$.9WAkrb7TdQJZKrHXVnS6eyEJ7iwBzbdiSyVoodMmsGTn2bdtH9wu', 1, 'todor@abv.bg', 0x68747470733A2F2F656E637279707465642D74626E302E677374617469632E636F6D2F696D616765733F713D74626E3A414E64394763544D39666E553268384A456B454F59514A72526F72377A48763032545F67644B74353372483362774A564444775F6B356A7A46772673, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (41, 'galia', '$2a$10$/y7KgZrB9eURe1jonafdOeCQQriFlyCk/BjFqMRm0dHAzU6ohMOtS', 1, 'galia@abv.bg', 0x687474703A2F2F736C61712E616D2F696D616765732F6E6577735F302F3232313132353736313335383439353635302E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (42, 'stanislav', '$2a$10$MSEOBzaYCazklLv5sEPdWOZbZGnXHMg.nqyK9.K7a0Y3ec9.ARt2m', 1, 'stanislav@abv.bg', 0x68747470733A2F2F737065636B79626F792E636F6D2F77702D636F6E74656E742F75706C6F6164732F323031312F31302F6361726963617475726561727434322E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (43, 'dimitar', '$2a$10$cMcqiVe.e6R9i2iqwOgVUeficbxh616Dtqq0bO/Fu/rkOEoTnAxVS', 1, 'dimitar@abv.bg', 0x68747470733A2F2F692E70696E696D672E636F6D2F6F726967696E616C732F36662F64622F61382F36666462613831313033383562616333373733623563356539636662613633362E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (44, 'pesho', '$2a$10$fE1sVtu2jaELeOiFE.dUkun5qORSr15zxdk5vcyKlHao7J2BG.IEK', 1, 'pesho@abv.bg', 0x68747470733A2F2F692E70696E696D672E636F6D2F6F726967696E616C732F31652F36392F32652F31653639326536363032333565333332643063636463336134663761383663632E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (45, 'nadya', '$2a$10$5u/cm1vDlsX0b.wY84dtxez3K3.4RlEme..Kaeqv.uri.nZGcYJZm', 1, 'nadya@abv.bg', 0x68747470733A2F2F692E70696E696D672E636F6D2F6F726967696E616C732F61312F35622F35362F61313562353663646639343366373136383032393130613263393261653033612E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (46, 'yavor', '$2a$10$iaZOCtKJHw6FuY5RBay.kubtPzGdr3V7HRE502qDaIFkjV74ahXuK', 1, 'yavor@gmail.com', 0x68747470733A2F2F7777772E6972616E636172746F6F6E2E636F6D2F736974652F6D656469612F75706C6F6164732F6461696C792F6C696F6E656C5F6D657373692E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (47, 'diana', '$2a$10$fZcLnkZQ837Wma3modeCYeKw1Ft8r5oGyLsjaYOwKbbiefoHNnKKC', 1, 'diana@abv.bg', 0x68747470733A2F2F6D69722D73332D63646E2D63662E626568616E63652E6E65742F70726F6A6563745F6D6F64756C65732F646973702F353533383362343939323039372E353630323063356233663966392E6A7067, null, null, null);
INSERT INTO beer_tag.users (id, username, password, enabled, email, photo, hash, value, passwordConfirmation) VALUES (48, 'user', '$2a$10$ORa49IJDAJUKCU2aW4dYP.ZZZ3X2yfIV8Y4KNDkMFk6Jf59inkGZ6', 1, 'user@abv.bg', 0x68747470733A2F2F706978656C37372E636F6D2F77702D636F6E74656E742F75706C6F6164732F323031352F30332F43656C6562726974792D43617269636174757265732D62792D4D61686573682D4E616D626961722D332D353730783737352E6A7067, null, null, null);