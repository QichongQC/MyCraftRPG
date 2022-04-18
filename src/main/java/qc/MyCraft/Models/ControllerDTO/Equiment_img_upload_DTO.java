package qc.MyCraft.Models.ControllerDTO;

import lombok.Data;

@Data
public class Equiment_img_upload_DTO {

    public Equiment_img_upload_DTO(Integer errno) {
        this.errno = errno;
        this.data = data;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public this_data getData() {
        return data;
    }

    public void setData(this_data data) {
        this.data = data;
    }

    Integer errno=0;
    this_data data;

    public class this_data{
        public this_data(String url, String alt, String href) {
            this.url = url;
            this.alt = alt;
            this.href = href;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        String url;
        String alt;
        String href;
    }
}
