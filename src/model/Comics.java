package model;

public class Comics extends Products {
        private int pageNumber;
        private String paperSize;
        private String language;

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public String getPaperSize() {
            return paperSize;
        }

        public void setPaperSize(String paperSize) {
            this.paperSize = paperSize;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
}
