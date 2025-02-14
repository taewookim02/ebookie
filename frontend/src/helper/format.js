export const formatYYYYMMDDKr = (dateStr) => {
    if (!dateStr) return "";

    const date = new Date(dateStr);
    return new Intl.DateTimeFormat('ko-KR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    }).format(date); // from YYYY-MM-DD => to YYYY년 MM월 DD일
}
