<script lang="ts">
    let {
        labelText,
        placeholder,
        value = $bindable(),
    }: { 
        labelText: string,
        placeholder: string,
        value: string,
    } = $props();
</script>

<div class="flex flex-row">
    <span class="basis-1/5">{labelText}</span>
    <input bind:value={value} class="px-2 py-1 rounded-md form-input grow" type="text" name="" id="" placeholder={placeholder}>
</div>
