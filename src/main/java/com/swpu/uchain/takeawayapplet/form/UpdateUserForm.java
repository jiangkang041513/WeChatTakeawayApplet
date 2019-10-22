package com.swpu.uchain.takeawayapplet.form;

import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class UpdateUserForm {

    private Long id;

    private String oldPw;

    private String newPw;

}
