CREATE VIEW view_user_gorup_role_mapping_info
AS
select
    `B`.`wf_user_group_name` AS `wf_user_group_name`,
    `D`.`name` AS `name`,
    `E`.`wf_role_code` AS `wf_role_code`,
    `E`.`wf_role_name` AS `wf_role_name`
from
    ((((`devops`.`wf_user_group_role_mapping` `A`
join `devops`.`wf_user_group` `B` on
    (`A`.`wf_user_group_id` = `B`.`wf_user_group_id`))
join `devops`.`wf_user_group_mapping` `C` on
    (`B`.`wf_user_group_id` = `C`.`wf_user_group_id`))
join `devops`.`wf_users` `D` on
    (`C`.`wf_user_id` = `D`.`user_id`))
join `devops`.`wf_role` `E` on
    (`A`.`wf_role_id` = `E`.`wf_role_id`));