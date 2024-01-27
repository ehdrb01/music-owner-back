CREATE VIEW view_user_role_mapping_info
AS
select
    `B`.`wf_user_type_id` AS `wf_user_type_id`,
    `B`.`wf_user_type_code` AS `wf_user_type_code`,
    `B`.`wf_user_type_name` AS `wf_user_type_name`,
    `C`.`wf_role_code` AS `wf_role_code`,
    `C`.`wf_role_name` AS `wf_role_name`,
    `C`.`wf_role_desc` AS `wf_role_desc`
from
    ((`devops`.`wf_user_type_role_mapping` `A`
join `devops`.`wf_user_type` `B` on
    (`A`.`wf_user_type_id` = `B`.`wf_user_type_id`))
join `devops`.`wf_role` `C` on
    (`A`.`wf_role_id` = `C`.`wf_role_id`));