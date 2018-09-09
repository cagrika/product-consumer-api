package com.ack.productconsumerapi.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

@Data
@Setter
@Getter
@Builder
public class ProductDto {

    @ApiModelProperty(hidden = true)
    private String id;
    private String name;
    private String detail;
    @ApiModelProperty(hidden = true)
    private String advertiserId;
    @ApiModelProperty(hidden = true)
    private Date createdDate;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", advertiserId='").append(advertiserId).append('\'');
        sb.append(", createdDate=").append(createdDate);
        sb.append('}');
        return sb.toString();
    }
}
