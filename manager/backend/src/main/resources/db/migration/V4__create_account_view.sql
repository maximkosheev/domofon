CREATE OR REPLACE VIEW public.v_account
AS SELECT oa.id,
    oa.account,
    oa.fio,
    oa.phone,
    oa.has_device,
    oa.device_switch_off,
    rc.name AS city_name,
    rs.name AS street_name,
    od.house,
    od.letter,
    od.building,
    od.porch,
    oa.flat,
    oa.create_dt,
    oa.con_dt,
    oa.discon_dt,
    oa.fsb,
    oa.gorod,
    oa.description
   FROM op_account oa
     JOIN op_domofon od ON oa.domofon_id = od.id
     JOIN ref_street rs ON od.street_id = rs.id
     JOIN ref_city rc ON rs.city_id = rc.id;
